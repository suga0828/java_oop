# Modelo de Dominio - Sistema de Biblioteca Universitaria

## Descripción

Este documento describe las clases del modelo de dominio del Sistema de Biblioteca Universitaria, que representan las
entidades principales y sus relaciones para la gestión de préstamos de libros.

## Entidades Principales

### 1. Country (País)

Representa el país de origen de autores y editoriales.

- **Atributos:**
  - `code`: Código del país (ej: "CO")
  - `name`: Nombre del país (ej: "Colombia")

### 2. Author (Autor)

Representa al escritor de uno o más libros.

- **Atributos:**
  - `name`: Nombre completo del autor
  - `country`: País de origen

### 3. Publisher (Editorial)

Representa la casa editorial que publica los libros.

- **Atributos:**
  - `code`: Código único de la editorial
  - `name`: Nombre de la editorial
  - `country`: País de origen

### 4. Book (Libro)

Representa un ejemplar de libro en la biblioteca.

- **Atributos:**
  - `ISBN`: Identificador único del libro
  - `title`: Título del libro
  - `edition`: Edición del libro
  - `author`: Autor del libro
  - `publisher`: Editorial que lo publicó
  - `borrowed`: Estado de préstamo (disponible/prestado)

### 5. Student (Estudiante)

Representa a un estudiante que puede sacar libros en préstamo.

- **Atributos:**
  - `id`: Identificación del estudiante
  - `name`: Nombre completo
  - `program`: Programa académico que cursa

### 6. Loan (Préstamo)

Representa el préstamo de un libro a un estudiante.

- **Atributos:**
  - `number`: Número único de préstamo
  - `loanDate`: Fecha de préstamo
  - `returnDate`: Fecha de devolución
  - `student`: Estudiante que realiza el préstamo
  - `book`: Libro prestado

### 7. Library (Biblioteca)

Clase principal que gestiona la colección de libros y préstamos.

- **Atributos:**
  - `books`: Lista de libros en la biblioteca
  - `loans`: Lista de préstamos activos
  - `nextLoanNumber`: Contador para números de préstamo

## Relaciones

- Un `Author` pertenece a un `Country` (1:1)
- Un `Publisher` pertenece a un `Country` (1:1)
- Un `Book` tiene un `Author` (1:1) y un `Publisher` (1:1)
- Un `Loan` asocia un `Book` con un `Student` (1:1:1)
- La `Library` contiene múltiples `Book` y `Loan` (1:N)

## Diagrama de Clases

```mermaid
classDiagram
    note for Country "País"
    class Country {
        -code : String
        -name : String
        +Country(code : String, name : String)

    }

    note for Author "Escritor"
    class Author {
        -name : String
        -country : Country
        +Author(name : String, country : Country)
    }

    note for Publisher "Editorial"
    class Publisher {
        -code : String
        -name : String
        -country : Country
        +Publisher(code : String, name : String, country : Country)
    }

    note for Book "Libro"
    class Book {
        -ISBN : String
        -title : String
        -edition : String
        -author : Author
        -publisher : Publisher
        -borrowed : boolean
        +Book(ISBN : String, title : String, edition : String, author : Author, publisher : Publisher)
        +setBorrowed(borrowed : boolean) : void
        +isBorrowed() : boolean
    }

   note for Student "Estudiante"
    class Student {
        -id : String
        -name : String
        -program : String
        +Student(id : String, name : String, program : String)
    }

    note for Loan "Préstamo"
    class Loan {
        -number : String
        -loanDate : Date
        -returnDate : Date
        -student : Student
        -book : Book
        +Loan(number : String, loanDate : Date, returnDate : Date, student : Student, book : Book)
    }

    note for Library "Biblioteca"
    class Library {
        -nextLoanNumber : int
        -books : Book[]
        -loans : Loan[]
        +addBook(book : Book) : void
        +registerLoan(loan: Loan) : void
        +findBook(ISBN : String) : Book
        +getNextLoanNumber() : String
    }

    Author "1" --> "1" Country
    Publisher "1" --> "1" Country
    Book "1" --> "1" Author
    Book "1" --> "1" Publisher
    Loan "1" --> "1" Student
    Loan "1" --> "1" Book
    Library "1" --> "*" Book
    Library "1" --> "*" Loan

```
