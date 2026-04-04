# Generador de Plantillas - Liga de Fútbol REST API

Una API REST desarrollada en Java con Spring Boot, diseñada para la gestión centralizada y eficiente de equipos, jugadores y usuarios en el contexto de una liga deportiva. 

Este proyecto demuestra la implementación de una arquitectura backend estructurada, aplicando validaciones de reglas de negocio, seguridad basada en tokens y persistencia de datos relacional.

## Stack Tecnológico

![Java](https://img.shields.io/badge/Java_17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.2-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## Características Principales

* **Seguridad y Control de Acceso (RBAC):** Implementación de autenticación y autorización Stateless mediante JSON Web Tokens (JWT). Los endpoints se encuentran protegidos y las modificaciones sobre los recursos operan bajo estrictas validaciones de roles (garantizando que solo un Administrador o el Propietario del equipo puedan realizar cambios).
* **Gestión de Dominios Relacionales:** Operaciones integrales para la administración de las entidades `Usuarios`, `Equipos` y `Jugadores`, optimizando las consultas a la base de datos PostgreSQL mediante Spring Data JPA.
* **Integridad y Reglas de Negocio:** Controles a nivel de capa de servicio para mantener la consistencia de los datos, incluyendo la restricción técnica de un máximo de 22 jugadores por plantilla.
* **Data Seeding Automatizado:** Inicialización automatizada que pre-carga la base de datos con un set de pruebas (generación dinámica de equipos y jugadores realistas) para facilitar las pruebas funcionales en entornos de desarrollo local.

## Resumen de la API

La aplicación expone una serie de endpoints para interactuar con los recursos principales:

* **Autenticación:** `POST /api/usuarios/login` para la validación de credenciales y emisión del token JWT.
* **Equipos:** Endpoints protegidos para listar, crear (`POST /api/equipos`) y modificar (`PUT /api/equipos/{id}`) plantillas, validando los headers de seguridad y propiedad.
* **Jugadores:** Gestión del ciclo de vida del jugador dentro del club, desde su alta (`POST /api/jugadores`) hasta su baja (`DELETE /api/jugadores/{id}`), consultando la disponibilidad del equipo en tiempo real.

---
*Desarrollado por Matias Meira*