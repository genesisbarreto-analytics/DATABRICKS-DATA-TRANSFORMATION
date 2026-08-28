# ⚙️ Databricks Jobs — Orchestration & Monitoring

> 🚧 **Work in Progress**

Este documento describe de forma portfolio-safe cómo estoy incorporando progresivamente notebooks de Databricks a **Jobs** para automatizar su ejecución y monitorear el resultado de las cargas.

> 🔒 Los nombres reales de Jobs, notebooks, clusters, catálogos, schemas, tablas, IDs y configuraciones internas fueron omitidos o reemplazados por elementos genéricos.

---

## 🎯 Objetivo

Automatizar la ejecución de notebooks de transformación de datos y contar con una forma controlada de:

- ejecutar procesos;
- programar cargas;
- monitorear estados;
- revisar duración;
- identificar errores;
- corregir y volver a ejecutar.

---

## 🏗️ Flujo de orquestación

`Databricks Job`

↓

`Notebook Task`

↓

`Compute`

↓

`Scala + Spark SQL`

↓

`Transformación y validaciones`

↓

`Delta Table`

↓

`Monitoring`

---

## 🔄 Ciclo de ejecución

El flujo utilizado actualmente contempla:

1. Validación manual del notebook.
2. Incorporación del notebook como tarea dentro de un Job.
3. Configuración del compute.
4. Ejecución manual para pruebas.
5. Programación mediante schedules o triggers.
6. Seguimiento del estado de la ejecución.
7. Revisión de logs y métricas cuando ocurre una falla.
8. Corrección del proceso.
9. Nueva ejecución.
10. Validación del resultado final.

---

## 📊 Estados monitoreados

Durante el seguimiento de las cargas se revisan principalmente:

### `Succeeded`

La tarea terminó correctamente y el proceso puede continuar con las validaciones definidas.

### `Failed`

La ejecución presentó un error y requiere revisión antes de volver a ejecutar.

---

## 🛠️ Elementos que estoy aplicando

- Creación y configuración de Databricks Jobs.
- Tasks basadas en notebooks.
- Ejecuciones manuales mediante `Run now`.
- Programación mediante schedules.
- Seguimiento de duración de los procesos.
- Monitoreo de estados `Succeeded` / `Failed`.
- Revisión de Spark UI, logs y métricas.
- Corrección y reejecución ante fallas.
- Validación posterior a la ejecución.

---

## 🔍 Manejo de errores

Una ejecución fallida no se considera únicamente como un error técnico, sino como una señal para revisar el proceso.

El flujo de revisión es:

`Failed`

↓

`Revisar mensaje de error`

↓

`Identificar causa`

↓

`Corregir notebook / configuración`

↓

`Ejecutar nuevamente`

↓

`Validar resultado`

↓

`Succeeded`

---

## ⏱️ Programación

Los procesos pueden configurarse para ejecutarse automáticamente mediante un schedule definido dentro de Databricks Jobs.

Para el portafolio no se publican horarios, nombres de ambientes ni configuraciones reales de infraestructura.

---

## 🧠 Aprendizajes aplicados

Actualmente estoy profundizando en:

- orquestación básica con Databricks Jobs;
- ejecución programada de notebooks;
- monitoreo operativo de procesos;
- interpretación de estados de ejecución;
- revisión de logs;
- resolución de fallas;
- automatización progresiva de cargas.

---

## 🚧 Estado

La automatización mediante Jobs continúa en evolución a medida que se incorporan nuevos notebooks y procesos a Databricks.

El objetivo es avanzar progresivamente desde ejecuciones manuales hacia procesos programados, monitoreados y reutilizables.

---

## 🔐 Privacidad

Este documento no contiene:

- Job IDs reales;
- nombres reales de Jobs;
- nombres de notebooks internos;
- nombres de clusters;
- nombres de catálogos o schemas;
- rutas privadas;
- credenciales;
- configuraciones empresariales;
- datos de clientes.

---

**Proyecto profesional en desarrollo · Documentación anonimizada para portafolio**
