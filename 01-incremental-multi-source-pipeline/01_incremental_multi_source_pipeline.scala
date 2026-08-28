// Databricks notebook source
// MAGIC %md
// MAGIC # Incremental Multi-Source Access Pipeline
// MAGIC
// MAGIC Portfolio-safe Databricks / Scala example.
// MAGIC Demonstrates parameterization, multi-source extraction, Spark transformations,
// MAGIC data-quality validations and Delta overwrite with post-write checks.

// COMMAND ----------

// MAGIC %md
// MAGIC ## 0. Parameters

// COMMAND ----------

dbutils.widgets.text("source_catalog", "analytics_source", "1. Source catalog")
dbutils.widgets.text("target_catalog", "analytics_dev", "2. Target catalog")
dbutils.widgets.text("target_schema", "portfolio", "3. Target schema")
dbutils.widgets.dropdown("confirm_write", "NO", Seq("NO", "YES"), "4. Write target table")

val sourceCatalog = dbutils.widgets.get("source_catalog").trim
val targetCatalog = dbutils.widgets.get("target_catalog").trim
val targetSchema  = dbutils.widgets.get("target_schema").trim
val confirmWrite  = dbutils.widgets.get("confirm_write").trim.toUpperCase

val sourceTable = s"${sourceCatalog}.raw_events.digital_interactions"
val targetTable = s"${targetCatalog}.${targetSchema}.fact_digital_access"

require(spark.catalog.tableExists(sourceTable), s"Source table does not exist: ${sourceTable}")

// COMMAND ----------

// MAGIC %md
// MAGIC ## 1. Source A extraction

// COMMAND ----------

val sourceA = spark.sql(s"""
    SELECT
        CAST(event_timestamp AS TIMESTAMP) AS event_timestamp,
        CAST(customer_id AS STRING) AS customer_id,
        CAST(user_id AS STRING) AS user_id,
        CAST(user_name AS STRING) AS user_name,
        CAST(user_type AS STRING) AS user_type,
        CAST(event_name AS STRING) AS event_name,
        CAST(channel AS STRING) AS channel
    FROM ${sourceTable}
    WHERE channel = 'CHANNEL_A'
      AND CAST(event_timestamp AS TIMESTAMP) >= ADD_MONTHS(CURRENT_DATE(), -36)
      AND CAST(event_timestamp AS TIMESTAMP) < CAST(DATE_ADD(CURRENT_DATE(), 1) AS TIMESTAMP)
""")

// COMMAND ----------

// MAGIC %md
// MAGIC ## 2. Source B extraction

// COMMAND ----------

val sourceB = spark.sql(s"""
    SELECT
        CAST(event_timestamp AS TIMESTAMP) AS event_timestamp,
        CAST(customer_id AS STRING) AS customer_id,
        CAST(user_id AS STRING) AS user_id,
        CAST(user_name AS STRING) AS user_name,
        CAST(user_type AS STRING) AS user_type,
        CAST(event_name AS STRING) AS event_name,
        CAST(channel AS STRING) AS channel
    FROM ${sourceTable}
    WHERE channel = 'CHANNEL_B'
      AND CAST(event_timestamp AS TIMESTAMP) >= ADD_MONTHS(CURRENT_DATE(), -36)
      AND CAST(event_timestamp AS TIMESTAMP) < CAST(DATE_ADD(CURRENT_DATE(), 1) AS TIMESTAMP)
""")

// COMMAND ----------

// MAGIC %md
// MAGIC ## 3. Consolidation and validation

// COMMAND ----------

import org.apache.spark.sql.functions._
import org.apache.spark.storage.StorageLevel

val finalDf = sourceA
    .unionByName(sourceB)
    .select(
        "event_timestamp",
        "customer_id",
        "user_id",
        "user_name",
        "user_type",
        "event_name",
        "channel"
    )
    .persist(StorageLevel.MEMORY_AND_DISK)

val summary = finalDf
    .agg(
        count(lit(1)).alias("total_rows"),
        min(col("event_timestamp")).alias("min_date"),
        max(col("event_timestamp")).alias("max_date")
    )
    .first()

val totalRows = summary.getAs[Long]("total_rows")
val maxDate   = summary.getAs[java.sql.Timestamp]("max_date")

require(totalRows > 0, "No records found after consolidation")
require(maxDate != null, "Unable to determine maximum event date")

val channels = finalDf
    .select(upper(trim(col("channel"))).alias("channel"))
    .distinct()
    .collect()
    .map(_.getString(0))
    .toSet

require(channels.contains("CHANNEL_A"), "CHANNEL_A is missing")
require(channels.contains("CHANNEL_B"), "CHANNEL_B is missing")

display(finalDf.groupBy("channel").count().orderBy("channel"))

// COMMAND ----------

// MAGIC %md
// MAGIC ## 4. Delta write with post-write validation

// COMMAND ----------

if (confirmWrite == "YES") {
    finalDf.write
        .format("delta")
        .mode("overwrite")
        .option("overwriteSchema", "true")
        .saveAsTable(targetTable)

    val targetSummary = spark.table(targetTable)
        .agg(
            count(lit(1)).alias("total_rows"),
            max(col("event_timestamp")).alias("max_date")
        )
        .first()

    val targetRows = targetSummary.getAs[Long]("total_rows")
    val targetMaxDate = targetSummary.getAs[java.sql.Timestamp]("max_date")

    require(targetRows == totalRows, s"Target row count mismatch: ${targetRows} vs ${totalRows}")
    require(targetMaxDate == maxDate, s"Target max date mismatch: ${targetMaxDate} vs ${maxDate}")

    println(s"Target table updated successfully: ${targetTable}")
} else {
    println("Validation completed. Target table was not overwritten.")
}

// COMMAND ----------

// MAGIC %md
// MAGIC ## 5. Cleanup

// COMMAND ----------

finalDf.unpersist()

println("Portfolio pipeline finished successfully")
