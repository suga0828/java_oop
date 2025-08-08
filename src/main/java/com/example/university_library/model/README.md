# Modelo de Dominio - Sistema de Biblioteca Universitaria

## Descripción

Este documento describe las clases del modelo de dominio del Sistema de Biblioteca Universitaria, que representan las
entidades principales y sus relaciones para la gestión de préstamos de libros.

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
    Loan "1" --> "1" Student : prestado a
    Loan "1" --> "1" Book : prestado
    Library "1" --> "*" Book : tiene
    Library "1" --> "*" Loan : tiene

```
