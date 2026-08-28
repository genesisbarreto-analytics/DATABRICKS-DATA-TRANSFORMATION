# 01 — Data Platform Migration | Incremental Pipelines

> 🚧 **Work in Progress**

Proyecto profesional actualmente en desarrollo orientado a la migración progresiva de procesos de datos hacia **Databricks**, utilizando notebooks en **Scala**, transformaciones con **Apache Spark / Spark SQL**, validaciones de calidad, tablas **Delta** y automatización mediante **Databricks Jobs**.

Este case study documenta mi aprendizaje aplicado durante la adaptación de procesos existentes hacia una arquitectura moderna de datos.

> 🔒 Los nombres de catálogos, schemas, tablas, canales y reglas internas han sido reemplazados por elementos genéricos.

---

## 🎯 Objetivo

Trasladar progresivamente procesos existentes de preparación y transformación de datos hacia Databricks, manteniendo la lógica de negocio y agregando controles que permitan validar la información antes y después de cada carga.

---

# 🧩 Contexto

El proyecto contempla diferentes procesos relacionados con información digital y operacional.

Para cada proceso se desarrolla o adapta un notebook que permite:

- consultar la fuente;
- aplicar filtros;
- transformar información;
- homologar estructuras;
- validar resultados;
- escribir una tabla analítica;
- automatizar posteriormente su ejecución.

---

# 🏗️ Arquitectura

`Fuentes existentes`

↓

`Databricks Notebook`

↓

`Scala + Spark SQL`

↓

`Transformación y reglas de negocio`

↓

`Validaciones`

↓

`Delta Tables`

↓

`Databricks Jobs`

↓

`Analytics / Power BI`

---

# 📚 Procesos trabajados

Para el portafolio, los procesos reales se representan mediante categorías genéricas:

| Categoría | Tipo de información |
|---|---|
| Digital Access | Accesos y uso de canales digitales |
| SIM Management | Gestión de SIM y equipos |
| Data Packages | Bolsas y paquetes de datos |
| Session Events | Eventos de sesión y navegación |
| Requests | Consultas y solicitudes |
| Usage & Consumption | Consumo móvil y fijo |
| Billing & Payments | Información relacionada con pagos |
| Customer Products | Productos y servicios de clientes |
| Roaming Services | Procesos asociados a roaming |
| Technical Support | Solicitudes y seguimiento técnico |
| Other Digital Services | Otras funcionalidades digitales |

---

# 💻 Ejemplo técnico

El repositorio incluye un notebook anonimizado que representa uno de los patrones utilizados durante la migración.

➡️ [Ver notebook Scala](./01_incremental_multi_source_pipeline.scala)

El notebook demuestra:

- parametrización mediante widgets;
- lectura desde una fuente de datos;
- extracción diferenciada por canal;
- transformación mediante Spark SQL;
- unión de DataFrames con `unionByName`;
- persistencia temporal con `MEMORY_AND_DISK`;
- validación de cantidad de registros;
- validación de fechas;
- control de orígenes esperados;
- escritura en formato Delta;
- validación posterior a la escritura;
- liberación de caché.

---

# 🛡️ Validaciones de calidad

Antes de realizar la escritura se revisan elementos como:

### Volumen

Validación de que el dataset final contenga registros.

### Fechas

Control de fecha mínima y máxima disponible.

### Orígenes

Validación de que los canales o fuentes esperadas estén presentes.

### Resultado final

Comparación entre el dataset transformado y la tabla escrita.

Esto permite detectar inconsistencias antes de considerar el proceso finalizado.

---

# ⚙️ Automatización con Databricks Jobs

Parte de los notebooks desarrollados se están incorporando progresivamente a **Databricks Jobs**.

Actualmente estoy trabajando con:

- ejecución manual para validaciones;
- configuración de tareas basadas en notebooks;
- programación mediante schedules;
- seguimiento de ejecuciones;
- estados `Succeeded` y `Failed`;
- revisión de duración;
- análisis de logs y métricas;
- corrección y reejecución ante errores.

---

# 🔄 Ciclo de trabajo

`Entender proceso existente`

→

`Adaptar lógica`

→

`Construir notebook`

→

`Validar`

→

`Generar Delta Table`

→

`Ejecutar Job`

→

`Monitorear`

→

`Mejorar`

---

# 🛠️ Tecnologías

`Databricks`

`Scala`

`Apache Spark`

`Spark SQL`

`Delta Lake`

`Databricks Jobs`

`Data Quality`

---

# 🧠 Aprendizajes aplicados

Este proyecto representa una nueva etapa dentro de mi desarrollo profesional.

Actualmente estoy profundizando en:

- arquitectura de procesos en Databricks;
- desarrollo de notebooks;
- Scala aplicado a transformación de datos;
- Spark DataFrames;
- Spark SQL;
- manejo de tablas Delta;
- validaciones de calidad;
- automatización mediante Jobs;
- monitoreo de ejecuciones.

---

# 🚧 Estado

El proyecto continúa **en desarrollo**.

A medida que avance la migración se podrán incorporar:

- nuevos notebooks;
- nuevos procesos automatizados;
- optimización de cargas;
- mayores controles de calidad;
- mejoras en Jobs;
- documentación adicional.

---

# 🔐 Privacidad

El contenido publicado es una versión **portfolio-safe**.

No se incluyen:

- nombres reales de catálogos;
- schemas internos;
- nombres reales de tablas;
- nombres de infraestructura;
- datos de clientes;
- identificadores;
- servidores;
- credenciales;
- URLs privadas;
- reglas empresariales sensibles.

---

**Proyecto profesional en desarrollo · Código y arquitectura anonimizados para portafolio**
