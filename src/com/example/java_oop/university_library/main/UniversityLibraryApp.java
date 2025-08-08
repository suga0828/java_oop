package com.example.java_oop.university_library.main;

import com.example.java_oop.university_library.model.*;

import java.util.Date;

/**
 * Clase principal de la aplicación del Sistema de Biblioteca Universitaria.
 */
public class UniversityLibraryApp {
    /**
     * Registra un mensaje con marca de tiempo.
     *
     * @param message Mensaje a registrar.
     */
    protected static void log(String message) {
        System.out.println("[" + new java.util.Date() + "] " + message);
    }
    
    /**
     * Punto de entrada de la aplicación.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Create countries
        Country country1 = new Country("AR", "Argentina");
        Country country2 = new Country("PE", "Perú");

        // Create authors
        Author author1 = new Author("Jorge Luis Borges", country1);
        Author author2 = new Author("Mario Vargas Llosa", country2);

        // Create publishers
        Publisher publisher1 = new Publisher("ED01", "Alfaguara", country1);

        // Create books
        Book book1 = new Book("978-84-204-7978-1", "Borges Esencial",
                            "3era edición Conmemorativa", author1, publisher1);
        Book book2 = new Book("84-204-8263-3", "Los cuadernos de Don Rigoberto", 
                            "1era edición", author2, publisher1);
        Book book3 = new Book("978-980-15-0447-4", "El sueño del celta", 
                            "1era edición", author2, publisher1);
        Book book4 = new Book("970-770-446-7", "Travesuras de la niña mala", 
                            "1era reimpresión", author2, publisher1);

        // Create student
        Student student1 = new Student("200582", "Alexander Sandoval", 
                                     "Ingeniería Informática");

        // Initialize library
        log("Inicializando biblioteca...\n");
        Library library = new Library();
  
        // Add books to library
        log("Agregando libros a la biblioteca...\n");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        // Generate loans
        log("=== PROCESO DE PRÉSTAMO ===");
        Date loanDate = new Date();
        Date returnDate = new Date(loanDate.getTime() + (7 * 24 * 60 * 60 * 1000)); // 7 days later
        
        // Create and add loans
        Loan loan1 = new Loan(library.getNextLoanNumber(), loanDate, returnDate, student1, book1);
        library.getLoans().add(loan1);
        log("Número de préstamo generado: " + loan1.getNumber());
        
        Loan loan2 = new Loan(library.getNextLoanNumber(), loanDate, returnDate, student1, book4);
        library.getLoans().add(loan2);
        log("Número de préstamo generado: " + loan2.getNumber());

        // Show library status
        log("=== ESTADO ACTUAL DE LA BIBLIOTECA ===\n");
        log("Resumen de libros:");
        for (Book book : library.getBooks()) {
            System.out.println(book.getTitle() + " - " + 
                            (book.isBorrowed() ? "PRESTADO" : "DISPONIBLE"));
        }
    }
}
