# Crud Pólizas Backend

API REST para la gestión de pólizas de inventario desarrollada con Spring Boot y PostgreSQL.

## Descripción

Este proyecto implementa un sistema de gestión de pólizas de inventario que permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre pólizas, empleados e inventario. La aplicación está diseñada para manejar el control de inventario a través de pólizas generadas por empleados.

## Características

- Gestión completa de pólizas de inventario
- Control de empleados
- Manejo de inventario
- Validaciones de datos
- Documentación de API con Swagger/OpenAPI
- Logging configurado con Logback
- Tests unitarios

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.4.2
- PostgreSQL
- Maven
- Lombok
- Spring Data JPA
- Spring Validation
- Swagger/OpenAPI
- JUnit

## Requisitos Previos

- Java JDK 21
- Maven
- PostgreSQL
- IDE (recomendado: IntelliJ IDEA, Eclipse o VS Code)

## Configuración del Proyecto

1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/crud-polizas-backend.git
```

2. Configurar la base de datos PostgreSQL:

- Crear una base de datos llamada inventario_db
- Actualizar las credenciales en application-dev.properties

3. Compilar el proyecto:
```bash
mvn clean install
```

4. CEjecutar la aplicación:
```bash
mvn spring-boot:run
```

## Estructura del Proyecto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/coppel/crud_polizas/
│   │       ├── controller/
│   │       ├── domain/
│   │       │   ├── dto/
│   │       │   └── entity/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
└── test/
```

## Endpoints de la API

La documentación completa de la API está disponible en:

```plaintext
http://localhost:8080/api/swagger-ui.html
```

