# 🏦 HexagonalMigrationPoC – Validación de Arquitectura para Migración

Este repositorio contiene el código fuente de la **Prueba de Concepto (PoC)** desarrollada para validar la viabilidad técnica de la estrategia de migración de datos. El objetivo principal fue demostrar la **coexistencia fluida** entre el sistema Legacy actual y la nueva infraestructura en la Nube, minimizando el impacto en el Core del negocio.

### 🔗 Contexto de Negocio
Para revisar el detalle del problema original, el desafío de los esquemas de datos heterogéneos (Inglés vs. Español) y el análisis de la solución, puede consultar el caso de estudio aquí:
👉 **[LinkedIn Post: El desafío del Cliente - Migración Legacy a Nube](https://www.linkedin.com/posts/hwongu_el-cliente-lleg%C3%B3-con-una-petici%C3%B3n-que-suena-activity-7427374631667568640-BUlm?utm_source=share&utm_medium=member_desktop&rcm=ACoAAAZyivkBpqjS-7ZvQubxyD0MzBUVYtR8T4k)**

---

## 📁 Estructura de la Solución

El proyecto está organizado siguiendo el patrón de **Arquitectura Hexagonal (Ports & Adapters)**, lo que garantiza que el cambio de base de datos sea transparente para el negocio:

### 1️⃣ 1_Domain (El Negocio)
Ubicación: `net.hwongu.prueba.domain`
Aquí reside la lógica pura de la organización.
* **Valor para el Cliente:** Esta capa es "agnóstica". No sabe si los datos vienen de un Excel, de Oracle o de la Nube. Esto protege la inversión: si cambiamos de proveedor de nube mañana, esta lógica no se toca.

### 2️⃣ 2_Application (La Orquestación)
Ubicación: `net.hwongu.prueba.application.service`
Contiene los flujos de trabajo validados en la PoC:
* **ConsultaSaldoService:** Recuperación unificada de saldos.
* **CrearClienteService:** Alta de clientes (demostrando la escritura en ambas fuentes).

### 3️⃣ 3_Infrastructure (La Solución Técnica)
Ubicación: `net.hwongu.prueba.infrastructure.adapter`
Aquí se encuentran los "traductores" que resuelven el conflicto de idiomas:
* **Legacy Adapter:** `ClienteRepositoryDbOnPremise`
    * Conecta con el sistema viejo.
    * Traduce tablas en Inglés (`business_name`) al modelo canónico.
* **Cloud Adapter:** `ClienteRepositoryDbCloud`
    * Conecta con el nuevo sistema.
    * Traduce tablas en Español (`razon_social`) al mismo modelo.

### 4️⃣ 4_DataBase (Scripts de Validación)
Ubicación: `/scripts`
Incluye los scripts SQL ejecutados durante la demostración para simular los dos entornos:
* `Db_OnPrememise.sql`: Crea el entorno On-Premise.
* `Db_Cloud.sql`: Crea el entorno Cloud.

---

## 📜 Licencia y Uso

Este código es propiedad intelectual de **Henry Wong** y se entrega como parte de los entregables de la consultoría para validación técnica.
Está permitido su uso para referencia interna del equipo de desarrollo y arquitectura.
Queda prohibido su uso en entornos productivos externos o su redistribución sin autorización.

---

## ⚠️ Nota Técnica

Este repositorio es una **Prueba de Concepto (PoC)**. Su finalidad es validar hipótesis de arquitectura, medir el esfuerzo de desarrollo y mitigar riesgos antes de la fase de implementación masiva. No incluye configuraciones de alta disponibilidad, seguridad perimetral ni monitoreo requeridos para el despliegue final en producción.
