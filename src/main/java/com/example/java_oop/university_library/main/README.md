# Sistema de Biblioteca Universitaria

## Descripción

Sistema de gestión de préstamos de libros para una biblioteca universitaria desarrollado en Java siguiendo los
principios de la Programación Orientada a Objetos.

## Características

- Gestión de libros, autores, editoriales y estudiantes
- Sistema de préstamos con seguimiento de fechas
- Búsqueda de libros por ISBN
- Generación automática de números de préstamo

## Estructura del Proyecto

```
university-library/
├── src/
│   └── main/
│       └── java/
│           └── co/
│               └── edu/
│                   └── umng/
│                       └── poo/
│                           └── university_library/
│                               ├── model/    # Clases del modelo de dominio
│                               └── main/     # Clase principal de la aplicación
└── README.md
```

## Diagrama de Clases

```mermaid
classDiagram
    class Country {
        -code: String
        -name: String
        +Country(code: String, name: String)
        +getCode() String
        +getName() String
    }

    class Author {
        -name: String
        -country: Country
        +Author(name: String, country: Country)
        +getName() String
        +getCountry() Country
    }

    class Publisher {
        -code: String
        -name: String
        -country: Country
        +Publisher(code: String, name: String, country: Country)
        +getCode() String
        +getName() String
        +getCountry() Country
    }

    class Book {
        -ISBN: String
        -title: String
        -edition: String
        -author: Author
        -publisher: Publisher
        -borrowed: boolean
        +Book(ISBN: String, title: String, edition: String, author: Author, publisher: Publisher)
        +getISBN() String
        +getTitle() String
        +getEdition() String
        +getAuthor() Author
        +getPublisher() Publisher
        +isBorrowed() boolean
        +setBorrowed(borrowed: boolean) void
    }

    class Student {
        -id: String
        -name: String
        -program: String
        +Student(id: String, name: String, program: String)
        +getId() String
        +getName() String
        +getProgram() String
    }

    class Loan {
        -number: String
        -loanDate: Date
        -returnDate: Date
        -student: Student
        -book: Book
        +Loan(number: String, loanDate: Date, returnDate: Date, student: Student, book: Book)
        +getNumber() String
        +getLoanDate() Date
        +getReturnDate() Date
        +getStudent() Student
        +getBook() Book
    }

    class Library {
        -nextLoanNumber: int
        -books: Book[]
        -loans: Loan[]
        +addBook(book: Book) void
        +findBook(ISBN: String) Book
        +getNextLoanNumber() String
        +log(message: String) void
        +getBooks() Book[]
        +getLoans() Loan[]
    }

    class LibraryApp {
        +main(args: String[]) void
    }

    Author "1" -- "1" Country : tiene
    Publisher "1" -- "1" Country : tiene
    Book "*" -- "1" Author : tiene
    Book "*" -- "1" Publisher : tiene
    Loan "*" -- "1" Book : referencia
    Loan "*" -- "1" Student : referencia
    Library "1" -- "*" Book : contiene
    Library "1" -- "*" Loan : registra
    LibraryApp --|> Library : extiende
```

## Compilación y Ejecución

### Requisitos

- Java 8 o superior
- Maven (opcional, pero recomendado)

### Compilación

```bash
# Navegar al directorio del proyecto
cd /ruta/a/university-library

# Compilar el proyecto
javac -d target/classes -sourcepath src/main/java src/main/java/co/edu/umng/poo/university_library/main/Main.java
```

### Ejecución

```bash
# Desde el directorio del proyecto
java -cp target/classes main.university_library.java_oop.UniversityLibraryApp
```

## Notas

- El sistema genera automáticamente números de préstamo secuenciales.
- Los libros marcados como prestados no pueden ser prestados nuevamente hasta ser devueltos.
- Se incluye un conjunto de datos de ejemplo para pruebas.
