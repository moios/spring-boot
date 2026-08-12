# Sistema de Gestión de Créditos

API REST para la gestión de clientes, productos financieros, créditos, contratos y pagos.

La API proporciona operaciones CRUD mediante endpoints REST y cuenta con documentación interactiva basada en Swagger / OpenAPI.

---

## Tabla de contenidos

- [Descripción](#descripción)
- [Arquitectura](#arquitectura)
- [Servidor](#servidor)
- [Documentación de la API](#documentación-de-la-api)
- [Recursos](#recursos)
  - [Clientes](#clientes)
  - [Productos](#productos)
  - [Créditos](#créditos)
  - [Contratos](#contratos)
  - [Pagos](#pagos)
- [Modelos](#modelos)
- [Ejemplo de consumo](#ejemplo-de-consumo)
- [Códigos HTTP](#códigos-http)
- [Resumen de endpoints](#resumen-de-endpoints)

---

## Descripción

El sistema expone una API REST destinada a la administración de un sistema de créditos financieros.

La API permite gestionar:

- Clientes
- Productos financieros
- Créditos
- Contratos de crédito
- Pagos

Las operaciones se realizan mediante los métodos HTTP estándar:

| Método | Operación |
|---|---|
| `GET` | Consultar información |
| `POST` | Crear recursos |
| `PUT` | Actualizar recursos |
| `DELETE` | Eliminar recursos |

---

## Arquitectura

La relación general entre los principales recursos del sistema puede representarse de la siguiente manera:

```mermaid
flowchart TD
    Cliente --> Credito
    Credito --> Contrato
    Credito --> Pago
    Credito --> Producto