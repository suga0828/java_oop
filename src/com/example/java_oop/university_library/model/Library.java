package com.example.java_oop.university_library.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el sistema de biblioteca universitaria.
 */
public class Library {
    private int nextLoanNumber = 1;
    private List<Book> books = new ArrayList<>();
    private List<Loan> loans = new ArrayList<>();

    /**
     * Genera el siguiente número de préstamo.
     * 
     * @return El siguiente número de préstamo como cadena formateada.
     */
    public String getNextLoanNumber() {
        return String.format("P%03d", nextLoanNumber++);
    }

    /**
     * @return La lista de todos los libros en la biblioteca.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * @return La lista de todos los préstamos activos.
     */
    public List<Loan> getLoans() {
        return loans;
    }

    /**
     * Agrega un libro a la colección de la biblioteca.
     *
     * @param book Libro a agregar.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Busca un libro por su ISBN.
     *
     * @param ISBN ISBN a buscar.
     * @return El libro con el ISBN dado, o null si no se encuentra.
     */
    public Book findBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }
}
