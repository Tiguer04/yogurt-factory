# 🥛 Yogurt Factory API

Sistema de gestión y monitoreo de producción de yogures desarrollado con Java 21 y Spring Boot. Permite simular el ciclo completo de fabricación de yogurt, desde la creación de recetas hasta el monitoreo en tiempo real de lotes en producción.

## 🚀 Aplicación desplegada en Render, echa un vistazo...

🔗 [https://yogurt-factory.onrender.com/swagger-ui/index.html](https://yogurt-factory.onrender.com/swagger-ui/index.html)

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.2.5 | Framework principal |
| Spring Data JPA | - | Acceso a datos |
| H2 Database | - | Base de datos en memoria |
| Lombok | 1.18.30 | Simplificación del código |
| Swagger / OpenAPI | 2.5.0 | Documentación de la API |
| Docker | - | Contenerización para despliegue en Render |
| Render | - | Plataforma de despliegue |

⭐ Recomendación: En el pom.xml configure la dependencia de Lombok con la versión recomendada
para Java 21, esto le ayudará a evitar problemas de compatibilidad en caso de que posea esta
versión de Java.

---

## 🔩 Arquitectura

El proyecto implementa una **Arquitectura en Capas** organizada en los siguientes paquetes:

```
com.miguelcardenas.demo
├── domain/
│   ├── model         → Entidades JPA (tablas de la base de datos)
│   ├── repository    → Acceso a datos con Spring Data JPA (Java Persistence API)
│   ├── service       → Lógica de negocio
│   └── controller    → Endpoints REST (Recibe peticiones y desencadena lógica de respuesta)
├── dto               → Objetos de transferencia de datos
└── exception         → Manejo centralizado de errores
```

---

## 📋 Endpoints principales

### 🥛 Gestión de Lotes de Yogurt `/api/batches`

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/batches` | Obtener todos los lotes |
| POST | `/api/batches` | Iniciar nuevo lote |
| GET | `/api/batches/{batchId}` | Obtener detalles de un lote (Obtener por Id) |
| POST | `/api/batches/{batchId}/heating` | Iniciar fase de calentamiento |
| POST | `/api/batches/{batchId}/inoculating` | Iniciar fase de inoculación |
| POST | `/api/batches/{batchId}/incubation` | Iniciar fase de incubación |
| POST | `/api/batches/{batchId}/refrigeration` | Iniciar fase de refrigeración |
| POST | `/api/batches/{batchId}/temperature` | Registrar temperatura |
| POST | `/api/batches/{batchId}/complete` | Completar lote (Lote terminado) |
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
- (Importante) Lombok 1.18.30

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

## 📁 Estructura del proyecto

Esta estructura hace énfasis en los archivos específicamente necesarios para el funcionamiento
correcto de la app. Omite documentos como el LICENSE, README.md, .gitignore, etc. También omite
los scripts para correr Maven sin tenerlo instalado.

Para más detalle, apreciar estructura completa en este repositorio.


```
yogurt-factory/
├── src/
│   └── main/
│       ├── java/com/miguelcardenas/demo/
│       │   ├── domain/
│       │   │   ├── controller/
│       │   │   │   ├── MonitoringController.java
│       │   │   │   ├── RecipeController.java
│       │   │   │   └── YogurtBatchController.java
│       │   │   ├── model/
│       │   │   │   ├── Ingredient.java
│       │   │   │   ├── Recipe.java
│       │   │   │   ├── TemperatureLog.java
│       │   │   │   └── YogurtBatch.java
│       │   │   ├── repository/
│       │   │   │   ├── RecipeRepository.java
│       │   │   │   ├── TemperatureLogRepository.java
│       │   │   │   └── YogurtBatchRepository.java
│       │   │   └── service/
│       │   │       ├── RecipeService.java
│       │   │       ├── TemperatureControlService.java
│       │   │       └── YogurtMakingService.java
│       │   ├── dto/
│       │   │   ├── BatchDTO.java
│       │   │   ├── IngredientDTO.java
│       │   │   ├── MonitoringDTO.java
│       │   │   ├── RecipeDTO.java
│       │   │   └── TemperatureRecordDTO.java
│       │   └── exception/
│       │       ├── BusinessException.java
│       │       └── GlobalExceptionHandler.java
│       └── resources/
│           └── application.properties
├── Dockerfile
└── pom.xml
```

## 📂 Documentación adicional

En la carpeta [`docs/`](docs/) se encuentran:
- Diagrama UML de clases del proyecto
- Evidencias del funcionamiento de la API en Swagger
- Evidencias del despliegue en Render
- Manual de usuario
  
---

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## 👨‍🏫 Créditos

Proyecto desarrollado bajo la guía de [@DanielDev87](https://github.com/DanielDev87)
