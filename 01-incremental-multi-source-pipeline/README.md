# 01 — Incremental Multi-Source Pipeline

Proyecto de aprendizaje aplicado en Databricks orientado a la consolidación de información proveniente de múltiples orígenes mediante Scala, Spark SQL y tablas Delta.

> 🚧 Work in Progress · Proyecto actualmente en desarrollo.

---

## 🎯 Objetivo

Adaptar un proceso existente de preparación de datos hacia Databricks, centralizando información de diferentes canales en una única estructura analítica.

---

## 🧩 Qué trabaja este notebook

- Parametrización mediante widgets.
- Lectura de información desde una tabla origen.
- Extracción diferenciada por canal.
- Aplicación de filtros y reglas de negocio.
- Estandarización de columnas.
- Unión de DataFrames con `unionByName`.
- Validación de volumen de registros.
- Validación de fechas mínima y máxima.
- Control de orígenes esperados.
- Persistencia temporal con `MEMORY_AND_DISK`.
- Escritura en formato Delta.
- Validación posterior a la escritura.
- Liberación de caché al finalizar el proceso.

---

## 🏗️ Flujo del proceso

`Fuente de datos`

↓

`Extracción por canal`

↓

`Transformación con Spark SQL`

↓

`Consolidación con unionByName`

↓

`Validaciones de calidad`

↓

`Delta Table`

↓

`Analytics / Power BI`

---

## 🔐 Control antes de escritura

El notebook incluye una validación previa que permite ejecutar todo el proceso sin reemplazar inmediatamente la tabla destino.

Esto permite revisar:

- cantidad de registros;
- fecha mínima;
- fecha máxima;
- distribución por origen;
- consistencia del resultado.

La escritura se ejecuta únicamente cuando el parámetro de confirmación está habilitado.

---

## 🛠️ Tecnologías

`Databricks` `Scala` `Apache Spark` `Spark SQL` `Delta Lake`

---

## 🧠 Aprendizajes aplicados

Este proyecto forma parte de mi proceso actual de aprendizaje en Databricks.

Estoy aplicando conocimientos relacionados con:

- creación y organización de notebooks;
- adaptación de lógica SQL hacia Scala/Spark;
- transformación de DataFrames;
- controles de calidad de datos;
- manejo de tablas Delta;
- automatización de ejecuciones mediante Databricks Jobs.

---

## 📄 Notebook

➡️ [Ver notebook Scala](./01_incremental_multi_source_pipeline.scala)

---

## 🔐 Privacidad

El notebook publicado corresponde a una versión anonimizada.

Los siguientes elementos fueron reemplazados:

- catálogos;
- schemas;
- nombres de tablas;
- identificadores;
- reglas internas;
- nombres de canales;
- datos empresariales.

---

**Proyecto profesional en desarrollo · Código anonimizado para portafolio**
