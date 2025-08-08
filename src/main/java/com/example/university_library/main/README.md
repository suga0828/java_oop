# Sistema de Biblioteca Universitaria

## Descripción

Sistema de gestión de préstamos de libros para una biblioteca universitaria desarrollado en Java siguiendo los
principios de la Programación Orientada a Objetos.

## Características

- Gestión de libros, autores, editoriales y estudiantes
- Sistema de préstamos con seguimiento de fechas
- Búsqueda de libros por ISBN
- Generación automática de números de préstamo

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
        +getName() : String
    }

    note for Publisher "Editorial"
    class Publisher {
        -code : String
        -name : String
        -country : Country
        +Publisher(code : String, name : String, country : Country)
        +getName() : String
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
        +getISBN() : String
        +getTitle() : String
        +getAuthor() : Author
        +getEdition() : String
        +getPublisher() : Publisher
        +isBorrowed() : boolean
        +setBorrowed(borrowed : boolean) : void
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
        +getNumber() : String
    }

    note for Library "Biblioteca"
    class Library {
        -nextLoanNumber : int
        -books : Book[]
        -loans : Loan[]
        +getNextLoanNumber() : String
        +getBooks() : Book[]
        +getLoans() : Loan[]
        +addBook(book : Book) : void
        +registerLoan(loan: Loan) : void
        +findBookByISBN(ISBN : String) : Book
    }

    %% Relationships
    Author "1" --> "1" Country : nacido en
    Publisher "1" --> "1" Country : ubicada en
    Book "1" --> "1" Author : escrito por
    Book "1" --> "1" Publisher : publicado por
    Loan "1" --> "1" Student : para
    Loan "1" --> "1" Book : prestado
    Library "1" --> "*" Book : tiene
    Library "1" --> "*" Loan : tiene

```

## Estructura del Proyecto

```
java_oop/
├── src/
│   └── com/
│       └── example/
│           └── university_library/
│               ├── model/       # Clases del modelo de dominio
│               │   ├── Country.java
│               │   ├── Author.java
│               │   ├── Publisher.java
│               │   ├── Book.java
│               │   ├── Student.java
│               │   ├── Loan.java
│               │   └── Library.java
│               └── main/        # Clase principal de la aplicación
│                   └── UniversityLibraryApp.java
└── README.md
```

## Compilación y Ejecución

### Requisitos

- Java 8 o superior

### Compilación

```bash
# Navegar al directorio del proyecto
cd /ruta/a/university-library

# Compilar el proyecto
javac -d out $(find src/main/java -name "*.java")
```

### Ejecución

```bash
# Desde el directorio del proyecto
java -cp out com.example.university_library.main.UniversityLibraryApp
```

## Notas

- El sistema genera automáticamente números de préstamo secuenciales.
- Los libros marcados como prestados no pueden ser prestados nuevamente hasta ser devueltos.
- Se incluye un conjunto de datos de ejemplo para pruebas.
