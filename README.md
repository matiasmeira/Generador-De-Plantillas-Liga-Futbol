# Generador de Plantillas — Liga de Fútbol ⚽️

Descripción

Backend en **Spring Boot** que gestiona plantillas de una liga de fútbol: equipos, jugadores y usuarios. Proyecto sencillo y listo para correr localmente; no requiere modificar el README para usarlo.

## Tecnologías principales 🔧

- Java 17
- Spring Boot 4.0.1
- Spring Data JPA
- PostgreSQL
- Maven (con `mvnw`)

## Endpoints REST principales 📡

- **Equipos**
  - GET  /api/equipos — Listar todos los equipos
  - POST /api/equipos?usuarioId={id} — Crear equipo; parámetros: `usuarioId` (query). Campos esperados en el body (Content-Type: application/json): `nombre`, `escudoUrl`.
  - PUT  /api/equipos/{id} — Actualizar equipo; headers requeridos: `X-User-Id`, `X-User-Role`.

- **Jugadores**
  - POST   /api/jugadores?equipoId={id} — Crear jugador en un equipo; parámetros: `equipoId` (query). Campos en body: `nombre`, `apellido`, `fechaNacimiento` (ISO yyyy-mm-dd), `dni`.
  - DELETE /api/jugadores/{id} — Eliminar jugador por id
  - GET    /api/jugadores/equipo/{equipoId} — Listar jugadores por equipo

- **Usuarios**
  - POST /api/usuarios — Crear usuario. Campos en body: `username`, `password`, `rol` (opcional)
  - GET  /api/usuarios — Listar usuarios
  - POST /api/usuarios/login — Login; envía `username` y `password` en el body (Content-Type: application/json). Devuelve 200 con el usuario si las credenciales son correctas o 401 en caso contrario.

> No se incluyen ejemplos de cuerpos JSON en este README por claridad; los campos necesarios se describen arriba.

## Ejemplos rápidos (curl) 🧪

- Listar equipos:

  curl -X GET http://localhost:8080/api/equipos

- Listar jugadores de un equipo (id = 1):

  curl -X GET http://localhost:8080/api/jugadores/equipo/1

- Eliminar jugador (id = 5):

  curl -X DELETE http://localhost:8080/api/jugadores/5

## Configuración por defecto ⚙️

Valores desde `src/main/resources/application.properties`:

- URL de base de datos: `jdbc:postgresql://localhost:5432/DBPlantillas` (variable `DATABASE_PUBLIC_URL`)
- Usuario por defecto: `postgres` (variable `PGUSER`)
- Contraseña por defecto: `matias` (variable `PGPASSWORD`)
- Puerto por defecto: `8080` (variable `PORT`)

Si prefieres usar otros valores, exporta las variables de entorno adecuadas antes de arrancar la aplicación.

## CORS

La configuración CORS en `CorsConfig.java` permite por defecto orígenes como `http://localhost:5173` y `https://*.up.railway.app`.

## Ejecutar localmente ▶️

1. Abre una terminal en la carpeta del proyecto
2. Ejecuta en Windows:
   - `mvnw.cmd spring-boot:run`
3. O en Unix/macOS:
   - `./mvnw spring-boot:run`
4. Alternativa: empaquetar y ejecutar el jar
   - `mvnw package`
   - `java -jar target/generador-plantillas-0.0.1-SNAPSHOT.jar`

## Tests ✅

Ejecuta las pruebas con:

- `mvnw test`

## Notas y mejoras posibles 💡

- Añadir autenticación robusta (JWT) y roles más estrictos
- Manejar validaciones y errores (códigos HTTP claros y mensajes)
- Añadir documentación de API si se desea (por ejemplo OpenAPI) — no incluida por defecto



