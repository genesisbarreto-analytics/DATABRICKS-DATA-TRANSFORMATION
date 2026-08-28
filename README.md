# Databricks Data Transformation — Work in Progress

Este proyecto representa una iniciativa actualmente en desarrollo para adaptar procesos existentes a Databricks utilizando Scala, Spark y tablas Delta.

## Contexto

El objetivo es trasladar procesos de preparación y consolidación de datos desde una arquitectura previa hacia Databricks, manteniendo reglas de negocio, validaciones y estructuras necesarias para reportería y análisis.

## Qué estoy aprendiendo y aplicando

- Creación de notebooks en Databricks.
- Uso de Scala con Spark SQL.
- Parametrización mediante widgets.
- Extracción desde múltiples orígenes.
- Unión de DataFrames con `unionByName`.
- Persistencia temporal con `MEMORY_AND_DISK`.
- Validaciones previas a escritura.
- Escritura en formato Delta.
- Validaciones posteriores a escritura.
- Ejecución de notebooks mediante Jobs.

## Flujo conceptual

`Fuentes existentes → Databricks Notebook → Transformación Spark/Scala → Validaciones → Delta Table → Power BI / Analytics`

## Estado

**Work in Progress**

Este repositorio no pretende mostrar experiencia avanzada en Databricks. Documenta un proceso de aprendizaje aplicado a un proyecto real de migración y modernización de datos.

## Archivo

➡️ [01_incremental_multi_source_pipeline.scala](./01_incremental_multi_source_pipeline.scala)

## Privacidad

Los nombres de catálogos, schemas, tablas, identificadores y reglas internas fueron reemplazados por nombres genéricos para proteger información confidencial.
