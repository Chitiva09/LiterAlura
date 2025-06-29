# # 📚 Challenge LiterAlura

Este proyecto fue desarrollado por **Camilo Bermeo** como parte del **Challenge LiterAlura**. Consiste en una aplicación backend con Java y Spring Boot que permite consultar libros desde la API pública de Gutendex, almacenarlos en una base de datos local (PostgreSQL) y exponer diversos endpoints RESTful para consultarlos, filtrarlos y analizarlos.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jackson (para deserialización JSON)
- HTTP Client nativo de Java
- Lombok
- Gutendex API (fuente externa)

---

## ⚙️ Arquitectura general

La arquitectura del proyecto está dividida en capas limpias:

- `controller`: Define los endpoints HTTP.
- `service`: Contiene la lógica de negocio.
- `client`: Se conecta a la API externa de Gutendex.
- `mapper`: Convierte entre entidades y DTOs.
- `entity`: Define los modelos de dominio persistentes.
- `dto`: Define objetos para comunicación externa (API y frontend).
- `repository`: Interfaces JPA para acceso a datos.

---

## 🧩 Estructura de clases

### 📘 Entidades

- `Book`: representa un libro. Contiene título, autores, categorías, idiomas, imagen y descripción.
- `Author`: representa un autor. Relación `@ManyToMany` con libros.

### 📤 DTOs

- `BookDto`: contiene el título, autores, categorías, idiomas, imagen, etc.
- `AuthorDto`: contiene nombre, año de nacimiento, muerte, y títulos de sus libros.
- `GutendexResponse`: mapea la respuesta de la API externa.

### 🔁 Mapper

- `BookMapper`: convierte entre `Book` y `BookDto`.
- `AuthorMapper`: convierte entre `Author` y `AuthorDto`.

### 🌐 Client

- `Client`: realiza la petición HTTP a la API de Gutendex, decodifica el JSON y devuelve una lista de `BookDto`.

### 🧠 Service

- `BookServiceImpl`: implementación principal de la lógica. Gestiona búsquedas, filtrado, almacenamiento y consultas.
- `BookService`: interfaz con las operaciones principales.

### 📡 Controller

- `BookController`: expone los endpoints REST.

---

## 🧪 Endpoints disponibles

### 🔍 Buscar libro por título

Consulta la API de Gutendex y guarda el primer resultado en la base de datos.

GET /books?search={title}

___
### 📚 Listar todos los libros guardados

GET /books/showBooks
___
### 👨‍💼 Listar todos los autores guardados
GET /books/showAuthors
___
### 📆 Filtrar autores vivos en cierto año

GET /books/author?year=YYYY

Retorna los autores que estaban vivos en ese año.
___

### 🌐 Filtrar libros por lenguaje

GET /books/by-language?language={language}

Ejemplo: `en`, `es`, `fr`.
___

## 🗃️ Base de datos

Se utiliza **PostgreSQL**. Las entidades `Book` y `Author` están relacionadas por una tabla intermedia `book_author` (relación `@ManyToMany`). El almacenamiento de datos se realiza una vez se consulta un libro por título y no existe en la base local.

---
## 🧠 Lógica destacada

- Si el libro ya existe por título en la base, no consulta a Gutendex.

- Si no existe, hace la petición HTTP, transforma la respuesta en DTO y luego en entidad persistente.

- Los autores también se gestionan: si ya existen en base, se reutilizan; si no, se crean.

- Los idiomas se almacenan como string plano, por lo que se usa like en las consultas.

___
## ❗ Validaciones y errores
- Validación de parámetros de búsqueda (title, language, year).

* Manejo de errores con respuestas claras:

  - 404 si no se encuentran libros/autores.

  - 400 si los parámetros no son válidos.

  - 500 si ocurre un error en la base de datos o interno.

- Uso de @Transactional para asegurar la integridad de las operaciones de guardado.

___

## ✅ Estado del proyecto
- Funcionalidad completa

- Documentación interna y externa

- Arquitectura limpia y modular

- Validaciones robustas

- Consultas filtradas por lenguaje y año

___

# 🧑‍💻 Autor
## Camilo Bermeo
Desarrollador autodidacta con enfoque en backend. Apasionado por Java, Spring Boot y el desarrollo de soluciones eficientes y limpias.
Este proyecto representa una integración completa de consumo de APIs externas, persistencia de datos y diseño de servicios RESTful modernos.