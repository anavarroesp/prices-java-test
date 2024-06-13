# Proyecto de Gestión de Precios

## Introducción

Este proyecto es un microservicio desarrollado para la gestión de precios de productos en diferentes cadenas.
Proporciona una API que permite consultar los precios de productos en función de diversos criterios como fecha, cadena y producto. 
Este microservicio está construido utilizando Spring Boot y sigue la arquitectura hexagonal para asegurar la separación de responsabilidades y facilitar el mantenimiento.

## Arquitectura

El proyecto sigue una arquitectura hexagonal (también conocida como Arquitectura de Puertos y Adaptadores), 
que facilita la separación de la lógica de negocio del código de infraestructura. 
Los componentes principales de la arquitectura del microservicio son los siguientes:

- **Domain**: Contiene la lógica de negocio pura y las entidades del dominio.
- **Application**: Contiene los casos de uso o servicios de aplicación que orquestan la lógica de negocio.
- **Infrastructure**: Configuraciones y componentes técnicos como la base de datos. Contiene los adaptadores que permiten la comunicación con el exterior.
- **API**: En este caso contiene la implementacion de los controladores REST que exponen los endpoints de la API.
- **Boot**: Clase principal de la aplicación que inicia el contexto de Spring Boot.

## Tecnologías Utilizadas

- **Java 22**: Lenguaje de programación utilizado.
- **Spring Boot**: Framework para construir la aplicación.
- **Spring Data JPA**: Abstracción de JPA para operaciones con bases de datos.
- **H2 Database**: Base de datos en memoria utilizada para pruebas.
- **JUnit 5**: Framework de pruebas unitarias.
- **Maven**: Herramienta de gestión de dependencias y construcción.

## Ejecución de la Aplicación

### Prerrequisitos

- Java 22 o superior
- Maven 3.6 o superior

### Instrucciones ejecucion local

1. **Clonar el repositorio**:

    ```sh
    git clone <URL_DEL_REPOSITORIO>
    cd prices
    ```

2. **Construir la aplicación**:

    ```sh
    mvn clean install
    ```

3. **Ejecutar la aplicación**:

    ```sh
    mvn spring-boot:run
    ```

4. **Acceder a la API**:

   La API estará disponible en `http://localhost:8080`.

### Ejecución de Pruebas

#### Pruebas Unitarias

Para ejecutar las pruebas unitarias, utiliza el siguiente comando:

```sh
mvn test
```

#### Pruebas con Postman

Para ejecutar las pruebas con Postman, importa el archivo `prices.postman_collection.json` 
que encontraras en la raiz del proyecto en Postman y ejecuta la colección.

