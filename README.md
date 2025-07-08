# Aplicación Msvc Zara

Se implementó una aplicación Spring Boot diseñada para gestionar información de precios,
se desarrollo con arquitectura hexagonal manteniendo el código limpio, escalable, 
manejo de errores, desacoplando cada capa utilizando puertos(interfaces) y utilizando mapper para 
pasar los datos de un tipo de objeto a otro como por ejemplo de un objeto entity otro objeto del dominio.

## Características

- Recuperar detalles de precios basados en parámetros de entrada.
- Manejo centralizado de excepciones.
- Pruebas de integración para el endpoint /api/price de la API.
- Uso de MapStruct para el mapeo entre entidades y modelos de dominio.
- Base de datos en memoria H2 para pruebas y desarrollo.

## Tecnologías Utilizadas

- **Java version 21**: Lenguaje de programación.
- **Spring Boot**: Framework para construir la aplicación.
- **Maven**: Herramienta de gestión de dependencias y construcción.
- **Base de datos H2**: Base de datos en memoria para pruebas.
- **MapStruct**: Para el mapeo de objetos.
- **JUnit 5**: Para pruebas.

## Estructura del Proyecto

- **`application`**: Servicios de aplicación y casos de uso.
- - **`mapper`**: Mapeadores de MapStruct para convertir entre modelos de dominio y entidades.
- - **`port`**: Interfaces que definen los puertos de entrada y salida de la aplicación.
  - - **`in`**: Puertos de entrada (interfaces) para los casos de uso.
  - - **`out`**: Puertos de salida (interfaces) para la persistencia y otros servicios externos.
- - **`service`**: Implementaciones de los casos de uso y lógica de negocio.
- **`domain`**: Lógica de negocio principal y modelos.
- - **`model`**: Clases de modelo que representan los datos del dominio.
- **`infrastructure`**: Implementaciones de la infraestructura.
- - **`adapter`**: Adaptadores para la infraestructura, como la configuración de la base de datos y el manejo de excepciones.
  - - **`entity`**: Entidades JPA que representan las tablas de la base de datos.
  - - **`exception`**: Excepciones personalizadas para la infraestructura.
  - - **`mapper`**: Mapeadores de MapStruct para convertir entre entidades y modelos de dominio.
  - - **`repository`**: Repositorios que extienden de JPA para acceder a la base de datos.
- - **`controller`**: Contiene los controladores REST para manejar las solicitudes de la API.
  - - **`dto`**: Objetos de transferencia de datos para las cargas útiles de solicitud y respuesta.
- - **`util`**: Utilidades y clases de ayuda para la infraestructura.

## Endpoints

### Endpoints de Precios

| Método HTTP | Endpoint          | Descripción                                   | Cuerpo de Solicitud                                                                                     | Cuerpo de Respuesta                                                                                                                                                   |
|-------------|-------------------|-----------------------------------------------|---------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**    | `/api/price`      | Recuperar detalles de precios según parámetros de entrada. | `{ "inputDate": "2020-06-14-10:00:00", "productId": 35455, "brandId": 1 }`                              | `{ "productId": 35455, "brandId": 1, "priceList": 1, "startDate": "2020-06-14-00:00:00", "endDate": "2020-12-31-23:59:59", "priceAmount": 35.50, "currency": "EUR" }` |
| **GET**     | `/api/price/{id}` | Recuperar detalles de precios por ID.         | Ninguno                                                                                                 | `{ "productId": 35455, "brandId": 1, "priceList": 1, "startDate": "2020-06-14-00:00:00", "endDate": "2020-12-31-23:59:59", "priceAmount": 35.50, "currency": "EUR" }`       |
| **GET**     | `/api/prices`     | Recuperar todos los detalles de precios.      | Ninguno                                                                                                 | `[ { "productId": 35455, "brandId": 1, "priceList": 1, "startDate": "2020-06-14-00:00:00", "endDate": "2020-12-31-23:59:59", "priceAmount": 35.50, "currency": "EUR" } ]`   |

## Información Adicional
1. Tenemos la documentación del API en el siguiente .yaml que se puede visualizar en swagger(https://editor.swagger.io/) Link: https://github.com/Edwin-Capillo/msvc-hexagonal-zara/blob/master/OpenAPI-swagger.yaml
2. Implementé 2 Endpoints adicionales al que pedían para dar mayor visibilidad a mi codificación.
3. Se implementó las pruebas unitarias con un coverage del +91% también se agregó las 5 pruebas de integración en la clase PriceControllerIntegrationTest
4. Se manejó el control de versiones con git, se revisó el Checkstyle y se escaneo el proyecto con SonarQube for IDE para detectar las vulnerabilidades obteniendo como resultado sin vulnerabilidades.

## Cómo Ejecutar

1. Clona el repositorio:
2. Luego de ejecutar el proyecto con intellij IDE pegar la siguiente url: http://localhost:8080/api/price en postman opción POST. 
   Utilicé PostMapping debido a que envío los parámetros en el body y es la mejor opción entre Get y Post para este caso con RequestBody
3. Formato del body:
   {
   "inputDate": "2023-06-14-10:00:00",
   "productId": 35455,
   "brandId": 1
   }
4. La solicitud retorna el objeto solicitado:
   {
   "productId": 35455,
   "brandId": 1,
   "priceList": 2,
   "startDate": "2020-06-14-15:00:00",
   "endDate": "2020-06-14-18:30:00",
   "priceAmount": 25.45,
   "currency": "EUR"
   }