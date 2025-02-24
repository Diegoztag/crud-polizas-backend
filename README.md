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


## Listado de Impacto

### Componentes del Sistema

1. #### **API REST (Backend)**

   ##### Controladores REST:
     * PolizaController: Gestión de operaciones CRUD para pólizas
     * EmpleadoController: Administración de empleados
     * InventarioController: Control de inventario y existencias

   ##### Servicios de negocio:
     * PolizaService: Lógica de negocio para pólizas
     * EmpleadoService: Gestión de empleados
     * InventarioService: Control de inventario

   ##### Manejo de excepciones personalizado:
     * ResourceNotFoundException
     * DuplicateResourceException

   ##### Validaciones con Jakarta Validation
   ##### Documentación con OpenAPI 3.0
   ##### Capa de persistencia con JPA/Hibernate

2. #### **Base de Datos (PostgreSQL)**

   ##### Tablas principales:
     * Polizas: Almacena registros de movimientos de inventario
     * Empleados: Gestión de empleados autorizados
     * Inventario: Control de productos y existencias

   ##### Relaciones entre entidades
   ##### Índices optimizados para consultas frecuentes

   ##### Tablas principales:

    * Diagrama ER
        https://app.eraser.io/workspace/2KDk5CCkFAAffneEWgiZ?origin=share

     * Polizas:
       - id_poliza serial (PK)
       - empleado_genero int not null (FK)
       - inventario_sku varchar(50) not null (FK) 
       - cantidad int not null check (Cantidad > 0)
       - activo boolean default true
       - fecha timestamp default current_timestamp
       - created_at timestamp default current_timestamp
     * Empleados:
       - id_empleado serial (PK)
       - nombre varchar(100) not null
       - apellido varchar(100) not null
       - puesto varchar(50) not null
       - created_at timestamp default current_timestamp
     * Inventario:
       - sku varchar(50) (PK)
       - nombre varchar(50) not null
       - cantidad int not null
       - created_at timestamp default current_timestamp

   ##### Relaciones:
     * Poliza(empleado_genero) -> Empleado(id_empleado) (Many-to-One)
     * Poliza(inventario_sku) -> Inventario(sku) (Many-to-One)

3. #### **Componentes de Seguridad**

   ##### Validación de datos:
     * @NotNull, @NotBlank para campos requeridos
     * @Min, @Size para validaciones de rango
     * @Positive para valores numéricos
     * @NotBlank para valore vacios

   ##### Manejo de excepciones:
     * GlobalExceptionHandler para control centralizado
     * Respuestas HTTP apropiadas

   ##### Logging:
     * Logback para registro de operaciones
     * Rotación de logs configurada

   ##### Trazabilidad:
     * created_at y updated_at en entidades
     * Registro de operaciones críticas
     * Eliminacion de pilozas logicas

4.  #### **Características Técnicas**

   ##### Arquitectura (Controllers, Services, Repositories, DTO), :
     * Controladores: Capa de presentación REST
     * Servicios: Lógica de negocio
     * Repositorios: Acceso a datos
     * DTOs: Transferencia de datos

   ##### Patrones de Diseño:
     * DTO Pattern
     * Repository Pattern
     * Builder Pattern (ApiResponseBuilder)
     * Inyección de dependencias

   ##### Tests:
     * Tests unitarios con JUnit y Mockito
     * Cobertura de servicios principales

   ##### Mapeo objeto-relacional con JPA

   5. #### **Herramientas de Desarrollo**

   ##### Control de versiones:
     * Git con .gitignore configurado

   ##### Gestión de dependencias:
     * Maven con pom.xml estructurado

   ##### Documentación:
     * Swagger UI en /api/swagger-ui.html

   ##### Monitoreo:
     * Logging con rotación de archivos

   ##### Desarrollo:
     * Spring Boot DevTools
     * Lombok para reducción de código boilerplate

