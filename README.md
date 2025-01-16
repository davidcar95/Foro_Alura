# Foro Alura

## Descripción del Proyecto

**Foro Alura** es una API REST desarrollada con Java y Spring Boot para gestionar un foro donde los usuarios pueden crear, listar, actualizar y eliminar tópicos. Además, los usuarios pueden interactuar con los tópicos creando, actualizando y eliminando respuestas. La API también incluye un sistema de autenticación utilizando JSON Web Tokens (JWT) para garantizar que solo usuarios autenticados puedan realizar acciones como crear o eliminar tópicos.

La base de datos utilizada en este proyecto es **PostgreSQL**, lo que permite almacenar de manera eficiente la información de usuarios, tópicos y respuestas.

## Tecnologías Utilizadas

- **Java**: El lenguaje de programación utilizado para desarrollar la API.
- **Spring Boot**: Framework para la creación rápida de aplicaciones en Java.
- **JSON Web Tokens (JWT)**: Para la autenticación y autorización de los usuarios.
- **PostgreSQL**: Sistema de gestión de bases de datos relacional utilizado para almacenar los datos del foro.
- **Insomnia**: Herramienta utilizada para probar los endpoints de la API.

## Funcionalidades

La API ofrece las siguientes funcionalidades:

- **Autenticación**: Los usuarios pueden autenticarse utilizando un token JWT.
- **Gestión de Tópicos**:
   - Crear un nuevo tópico.
   - Listar todos los tópicos.
   - Buscar un tópico por ID.
   - Actualizar un tópico.
   - Eliminar un tópico.
- **Gestión de Respuestas**:
   - Crear una respuesta a un tópico.
   - Listar respuestas de un tópico.
   - Eliminar una respuesta de un tópico.
- **Documentación interactiva**:
   - Endpoint para probar la API con Swagger: /swagger-ui.html

## Lista de Endpoints

### Autenticación

- **POST** `/login`

  Autentica a un usuario y devuelve un token JWT.

  **Body**:
  ```json
  {
    "email": "ejemplo@correo.com",
    "contrasenha": "123456"
  }

### Topicos

- **POST** `/topicos`

  Crea un nuevo tópico.

  **Body**:
  ```json
  {
  "usuarioId": {id},
  "titulo": "test",
  "mensaje": "test",
  "status": "Abierto",
  "curso": "curso"
  }

- **GET** `/topicos/topicoPorId/{id}`

  Busca un tópico por ID.

- **PUT** `/topicos/{id}`

  Actualiza un tópico.

  **Body**:
  ```json
  {
  "usuarioId": {id},
  "topicoId": {id},
  "titulo": "Nuevo título",
  "mensaje": "Mensaje actualizado del tópico test"
  }

- **DELETE** `/topicos/{id}`

  Elimina un tópico.

- **GET** `/topicos`

  Lista todos los tópicos.

### Respuestas

- **GET** `/topicos/{id}`

  Lista las respuestas de un tópico por ID.

- **GET** `/topicos/{id}/respuestas`

  Lista todas las respuestas de todos los tópicos.

- **DELETE** `/topicos/{id}/{idrespuesta}`

  Elimina una respuesta de un tópico.

  **Body**:
  ```json
  {
  "respuestaId": {id}
  }

- **POST** `/topicos/{id}`

  Crea una nueva respuesta en un tópico.

  **Body**:
  ```json
  {
  "topicoId": {id},
  "usuarioId": {id},
  "mensaje": "Esta es una nueva respuesta."
  }

## Uso de Insomnia

1. **Autenticación:** Antes de hacer peticiones que modifiquen los datos (como crear, actualizar o eliminar tópicos), debes autenticarte utilizando el endpoint de login y obtener el token JWT.

2. **Headers de Autenticación:** En Insomnia, al realizar una solicitud que requiera autenticación, debes incluir el token JWT en los headers de la siguiente manera:

- **Authorization:** Bearer {token}
3. **Pruebas de Endpoints:**

- Puedes probar los endpoints con Insomnia enviando las solicitudes HTTP correspondientes (GET, POST, PUT, DELETE).
- Asegúrate de incluir el token JWT para las operaciones que lo requieran.

### Respuestas Esperadas

- **201 Created:** Cuando se crea un recurso, como un tópico o una respuesta.
- **200 OK:** Cuando se obtiene correctamente un recurso o se realiza una operación satisfactoria.
- **400 Bad Request:** Cuando la solicitud es incorrecta.
- **403 Forbidden:** Cuando no se proporciona un token JWT válido o no autorizado.

## Requerimientos para Ejecutar el Proyecto

### Dependencias

1. **Java 17**
2. **Spring Boot:** Para crear la API REST.
3. **PostgreSQL:** Para gestionar la base de datos.
4. **JWT (Json Web Token):** Para la autenticación de usuarios.

### Configuración de la Base de Datos

Asegúrate de configurar PostgreSQL y crear una base de datos para el proyecto. Luego, en el archivo application.properties, configura la conexión a la base de datos:
```bash
spring.application.name=foroAlura

api.security.secret=${apiSecret}

spring.datasource.url=jdbc:postgresql://localhost:5432/foro_alura
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true

spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.locations=classpath:db/migrations
```
### Ejecución

1. Clona el repositorio del proyecto.

2. Asegúrate de tener PostgreSQL instalado y corriendo en tu máquina local.

3. Configura la conexión a la base de datos en el archivo application.properties.

4. Ejecuta el proyecto con el siguiente comando:

```bash
./mvnw spring-boot:run
```
5. Accede a los endpoints utilizando herramientas como Insomnia para realizar pruebas.

6. **Acceder a la documentación:** Abrir el navegador en: http://localhost:8080/swagger-ui.html
## Agradecimientos

Agradecemos a todos los colaboradores y a la comunidad de desarrollo por su apoyo en la creación y mejora de este proyecto. ¡Gracias por ser parte de esta comunidad de aprendizaje!

