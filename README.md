# 🖥️ Backend Sysman

Proyecto backend desarrollado con **Jakarta EE**, desplegado en **Tomcat** y gestionado con **Maven**.  
Incluye una API REST para la gestión de tareas, conexión a base de datos **Oracle XE** mediante Docker, y soporte para CORS.

---

## 📂 Estructura del proyecto

backend/
├── src/
│   ├── main/
│   │   ├── java/com/sysman/test/backend/
│   │   │   ├── dao/
│   │   │   ├── filter/   # CorsFilter
│   │   │   ├── model/    # Entidades (Task)
│   │   │   ├── resource/ # Controladores REST
│   │   │   ├── service/  # Lógica de negocio
│   │   │   └── util/
│   │   ├── resources/
│   │   └── webapp/
│   └── test/
├── pom.xml
├── Dockerfile
├── docker-compose.yml
└── requests.http



---

## ⚙️ Tecnologías utilizadas

- **Java 17**
- **Jakarta EE 10 / Servlet 6.0**
- **Jersey 3.1.5** (para REST)
- **Oracle XE 21c** (Docker)
- **Maven** (gestión de dependencias y build)
- **JUnit 5** (testing)
- **Tomcat** (servidor de despliegue)

---

## 🚀 Configuración y ejecución

### 1. Levantar la base de datos Oracle XE con Docker
bash
docker-compose up -d
Esto crea un contenedor oracle-db con:

Puerto: 1521

Usuario: system

Password: oracle

Script de inicialización: db/init.sql

2. Compilar y empaquetar con Maven
bash
mvn clean package
Genera el archivo backend.war en la carpeta target/.

3. Desplegar en Tomcat
Copiar el .war en la carpeta webapps/ de Tomcat.
La API quedará disponible en:

Code
http://localhost:8080/backend_war_exploded/api/tasks
🌐 API REST - Endpoints
📋 Obtener todas las tareas
http
GET /api/tasks
Respuesta: Lista de tareas en formato JSON.

🔍 Obtener tarea por ID
http
GET /api/tasks/{id}
Respuesta: Objeto Task si existe, o 404 Not Found.

➕ Crear tarea
http
POST /api/tasks
Content-Type: application/json

{
  "title": "Prueba técnica",
  "description": "Crear API REST con Jakarta EE",
  "completed": 0
}
Respuesta: 201 Created si se crea correctamente.

✏️ Actualizar tarea
http
PUT /api/tasks/{id}
Content-Type: application/json

{
  "title": "Prueba actualizada",
  "description": "Actualizar tarea",
  "completed": 1
}
Respuesta: 200 OK si se actualiza correctamente.

🗑️ Eliminar tarea
http
DELETE /api/tasks/{id}
Respuesta: 200 OK si se elimina correctamente.

🔒 CORS
El proyecto incluye un filtro (CorsFilter) que habilita peticiones desde http://localhost:4200 (Angular frontend).
Permite métodos: GET, POST, PUT, DELETE, OPTIONS.

👨‍💻 Autor
Martin  
Proyecto de práctica técnica con enfoque en arquitectura backend moderna y documentación clara para reclutadores y líderes técnicos.
