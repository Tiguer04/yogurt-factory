# 🥛 Yogurt Factory API

Sistema de gestión y monitoreo de producción de yogures desarrollado con Java 21 y Spring Boot. Permite simular el ciclo completo de fabricación de yogurt, desde la creación de recetas hasta el monitoreo en tiempo real de lotes en producción.

## 🚀 Demo en vivo

🔗 [https://yogurt-factory.onrender.com/swagger-ui/index.html](https://yogurt-factory.onrender.com/swagger-ui/index.html)

> **Nota:** La aplicación está desplegada en el plan gratuito de Render. Si no responde de inmediato, espera unos segundos mientras el servidor despierta.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.x | Framework principal |
| Spring Data JPA | - | Acceso a datos |
| H2 Database | - | Base de datos en memoria |
| Lombok | - | Reducción de código boilerplate |
| Swagger / OpenAPI | - | Documentación de la API |
| Docker | - | Contenerización para despliegue |
| Render | - | Plataforma de despliegue |

---

## 🏗️ Arquitectura

El proyecto implementa una **Arquitectura en Capas** organizada en los siguientes paquetes:

```
com.miguelcardenas.demo
├── model         → Entidades JPA (tablas de la base de datos)
├── repository    → Acceso a datos con Spring Data JPA
├── service       → Lógica de negocio
├── controller    → Endpoints REST
├── dto           → Objetos de transferencia de datos
└── exception     → Manejo centralizado de errores
```

---

## 📋 Endpoints principales

### 🥛 Gestión de Lotes de Yogurt `/api/batches`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/batches` | Obtener todos los lotes |
| POST | `/api/batches` | Iniciar nuevo lote |
| GET | `/api/batches/{batchId}` | Obtener detalles de un lote |
| POST | `/api/batches/{batchId}/heating` | Iniciar fase de calentamiento |
| POST | `/api/batches/{batchId}/inoculating` | Iniciar fase de inoculación |
| POST | `/api/batches/{batchId}/incubation` | Iniciar fase de incubación |
| POST | `/api/batches/{batchId}/refrigeration` | Iniciar fase de refrigeración |
| POST | `/api/batches/{batchId}/temperature` | Registrar temperatura |
| POST | `/api/batches/{batchId}/complete` | Completar lote |
| POST | `/api/batches/{batchId}/fail` | Marcar lote como fallido |

### 📖 Gestión de Recetas `/api/recipes`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/recipes` | Obtener todas las recetas |
| POST | `/api/recipes` | Crear nueva receta |
| GET | `/api/recipes/{id}` | Obtener receta por ID |
| PUT | `/api/recipes/{id}` | Actualizar receta existente |
| PATCH | `/api/recipes/{id}/activate` | Activar receta |
| PATCH | `/api/recipes/{id}/deactivate` | Desactivar receta |
| GET | `/api/recipes/search` | Buscar recetas por palabra clave |

### 📊 Monitoreo de Producción `/api/monitoring`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/monitoring/dashboard` | Dashboard general de monitoreo |
| GET | `/api/monitoring/batches/active` | Obtener lotes activos |
| GET | `/api/monitoring/batches/{batchId}/temperature` | Resumen de temperatura por lote |
| GET | `/api/monitoring/batches/{batchId}/temperature-logs` | Historial de registros de temperatura |

---

## ▶️ Cómo ejecutar localmente

### Prerrequisitos
- Java 21
- Maven 3.9+

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/Tiguer04/yogurt-factory.git
cd yogurt-factory

# 2. Ejecutar la aplicación
./mvnw spring-boot:run

# 3. Abrir Swagger en el navegador
# http://localhost:8080/swagger-ui/index.html
```

---

## 🐳 Ejecutar con Docker

```bash
# 1. Construir la imagen
docker build -t yogurt-factory .

# 2. Correr el contenedor
docker run -p 8080:8080 yogurt-factory

# 3. Abrir Swagger
# http://localhost:8080/swagger-ui/index.html
```

---

## 📁 Estructura del proyecto

```
yogurt-factory/
├── src/
│   └── main/
│       ├── java/com/miguelcardenas/demo/
│       │   ├── model/
│       │   │   ├── Ingredient.java
│       │   │   ├── Recipe.java
│       │   │   ├── TemperatureLog.java
│       │   │   └── YogurtBatch.java
│       │   ├── repository/
│       │   ├── service/
│       │   ├── controller/
│       │   ├── dto/
│       │   └── exception/
│       └── resources/
│           └── application.properties
├── Dockerfile
├── pom.xml
├── LICENSE
└── README.md
```

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.
