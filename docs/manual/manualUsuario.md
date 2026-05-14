# Manual de Usuario — Yogurt Factory API

## 1. Introducción

Yogurt Factory API es un sistema de gestión y monitoreo de producción de yogures. Permite simular el ciclo completo de fabricación, desde la creación de recetas hasta el seguimiento en tiempo real de lotes en producción.

Este manual explica cómo interactuar con la API a través de Swagger UI, especialmente
dirigido para nuevos usuarios.

---

## 2. Acceso a la aplicación

La aplicación está disponible en:

🔗 [https://yogurt-factory.onrender.com/swagger-ui/index.html](https://yogurt-factory.onrender.com/swagger-ui/index.html)

---

## 3. Interfaz de Swagger UI

Al ingresar a la aplicación verás la interfaz de Swagger UI con tres secciones principales:

- **Gestión de Lotes de Yogurt** — operaciones sobre los lotes de producción
- **Gestión de Recetas** — operaciones para administrar recetas de yogurt
- **Monitoreo de Producción** — seguimiento en tiempo real del proceso

Para expandir cualquier sección, haz clic sobre su título. Para ejecutar un endpoint, haz clic sobre él, luego en **Try it out**, completa los datos requeridos y presiona **Execute**.

Para facilitar su experiencia, cada endpoint cuenta con documentación y descripciones
claras de su funcionamiento.

---

## 4. Flujo completo de producción

El flujo recomendado para producir un lote de yogurt es el siguiente:

```
1. Crear una receta (incluye sus ingredientes)
2. Iniciar un nuevo lote usando esa receta y rellenando los datos del DTO.
    - El lote iniciado comienza con un "status: PREPARING".
3. Iniciar fase de calentamiento
4. Iniciar fase de inoculación
    - El lote ya debió estar en "status: COOLING".
    - Esto sucede automáticamente.
5. Iniciar fase de incubación
    - El lote ya debió estar en "status: INOCULATING".
6. Registrar temperaturas durante el proceso.
    - Esto sucede cada que inicias una nueva fase en el lote
      o si quieres registrarla manualmente también es posible.
7. Iniciar fase de refrigeración
    - El lote ya debió estar en "status: INCUBATING".
8. Completar el lote
```

---

## 5. Gestión de Recetas

### 5.1 Crear una receta

**Endpoint:** `POST /api/recipes`

Ejemplo de datos a enviar:

```json
{
  "name": "Yogurt Griego Tradicional",
  "description": "Yogurt de textura espesa y alto contenido proteico",
  "defaultMilkVolume": 10,
  "defaultStarterAmount": 20,
  "heatingTemperature": 85,
  "heatingDuration": 30,
  "inoculationTemperature": 42,
  "incubationTemperature": 43,
  "minIncubationTime": 360,
  "maxIncubationTime": 720,
  "refrigerationTime": 240,
  "difficulty": "BEGINNER",
  "tips": "Filtrar con tela de queso para mayor consistencia",
  "ingredients": [
    {
      "name": "Leche entera",
      "quantity": 10.5,
      "unit": "ml",
      "notes": "Debe estar a temperatura ambiente antes de mezclar",
      "optional": false
    }
  ]
}
```

Los valores posibles para `difficulty` son: `BEGINNER`, `INTERMEDIATE`, `ADVANCED`.

Recuerde: Las recetas creadas, en primera instancia, poseen un "active: true", para
desactivarlas, una por una, debe de ir al **Endpoint:** `PATCH /api/recipes/{id}/deactivate`.


---

### 5.2 Obtener todas las recetas

**Endpoint:** `GET /api/recipes`

No requiere ningún dato de entrada. Retorna la lista completa de recetas registradas.

---

### 5.3 Buscar recetas por palabra clave

**Endpoint:** `GET /api/recipes/search`

Parámetro requerido:

| Parámetro | Descripción | Ejemplo |
|---|---|---|
| `keyword` | Palabra a buscar en nombre o descripción | `griego` |

---

### 5.4 Actualizar una receta

**Endpoint:** `PUT /api/recipes/{id}`

Reemplaza `{id}` con el ID numérico de la receta que deseas modificar y envía los nuevos datos en el mismo formato que al crearla.

---

### 5.5 Activar / Desactivar una receta

**Endpoint:** `PATCH /api/recipes/{id}/activate`
**Endpoint:** `PATCH /api/recipes/{id}/deactivate`

Solo requiere el `{id}` de la receta en la URL.

---

## 6. Gestión de Lotes de Yogurt

### 6.1 Iniciar un nuevo lote

**Endpoint:** `POST /api/batches`

Ejemplo de datos a enviar:

```json
{
  "recipeId": 1,
  "customMilkVolume": 50.5,
  "customStarterAmount": 3
}
```

La respuesta incluirá el `batchId` que necesitarás para todas las operaciones siguientes.

---

### 6.2 Fases del proceso de producción

Cada fase se activa en orden con su respectivo endpoint. Todas reciben el `{batchId}` en la URL.

| Fase | Endpoint | Descripción |
|---|---|---|
| Calentamiento | `POST /api/batches/{batchId}/heating` | Inicia el calentamiento |
| Inoculación | `POST /api/batches/{batchId}/inoculating` | Agrega el cultivo iniciador |
| Incubación | `POST /api/batches/{batchId}/incubation` | Periodo de fermentación |
| Refrigeración | `POST /api/batches/{batchId}/refrigeration` | Enfriamiento final |

---

### 6.3 Registrar temperatura

**Endpoint:** `POST /api/batches/{batchId}/temperature`

Ejemplo de datos a enviar:

```json
{
  "temperature": 42.5,
  "type": "INCUBATION"
}
```

---

### 6.4 Completar o marcar como fallido un lote

| Acción | Endpoint |
|---|---|
| Completar lote exitosamente | `POST /api/batches/{batchId}/complete` |
| Marcar lote como fallido | `POST /api/batches/{batchId}/fail` |

---

### 6.5 Consultar un lote

**Endpoint:** `GET /api/batches/{batchId}`

Retorna todos los detalles del lote especificado.

---

## 7. Monitoreo de Producción

### 7.1 Dashboard general

**Endpoint:** `GET /api/monitoring/dashboard`

Muestra un resumen general del estado de toda la producción: lotes en cada fase y métricas generales.

---

### 7.2 Lotes activos

**Endpoint:** `GET /api/monitoring/batches/active`

Retorna todos los lotes que están actualmente en proceso de producción.

---

### 7.3 Resumen de temperatura por lote

**Endpoint:** `GET /api/monitoring/batches/{batchId}/temperature`

Muestra un resumen de las temperaturas registradas para un lote específico.

---

### 7.4 Historial de registros de temperatura

**Endpoint:** `GET /api/monitoring/batches/{batchId}/temperature-logs`

Retorna el historial completo de todos los registros de temperatura de un lote.

---

## 8. Códigos de respuesta posibles

| Código | Significado |
|---|---|
| `200` | Operación exitosa |
| `201` | Recurso creado exitosamente |
| `400` | Datos enviados incorrectos |
| `404` | Recurso no encontrado |
| `500` | Error interno del servidor |

---

## 9. Consideraciones importantes

- La base de datos es **en memoria (H2)**. Esto significa que los datos se pierden cada vez que el servidor se reinicia o entra en modo inactivo.
- Se recomienda crear al menos una receta antes de intentar iniciar un lote de producción, sino, no podrá crearlo.
- Apéguese al orden de las fases de producción: calentamiento → inoculación → incubación → refrigeración.

---

*Desarrollado por Miguel Cardenas — Proyecto guiado por [@DanielDev87](https://github.com/DanielDev87)*