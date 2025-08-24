package com.example.university_library.model;

/**
 * Representa un libro en el sistema de biblioteca universitaria.
 */
public class Book {
    private final String ISBN;
    private final String title;
    private final String edition;
    private final Author author;
    private final Publisher publisher;
    private boolean borrowed;

    /**
     * Crea una nueva instancia de Libro.
     *
     * @param ISBN      ISBN del libro.
     * @param title     Título del libro.
     * @param edition   Edición del libro.
     * @param author    Autor del libro.
     * @param publisher Editorial del libro.
     */
    public Book(String ISBN, String title, String edition, Author author, Publisher publisher) {
        this.ISBN = ISBN;
        this.title = title;
        this.edition = edition;
        this.author = author;
        this.publisher = publisher;
        this.borrowed = false;
    }

    /**
     * @return El ISBN del libro.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @return El título del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return El autor del libro.
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @return La edición del libro.
     */
    public String getEdition() {
        return edition;
    }

    /**
     * @return La editorial del libro.
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * @return Verdadero si el libro está prestado, falso en caso contrario.
     */
    public boolean isBorrowed() {
        return borrowed;
    }

    /**
     * Establece el estado de préstamo del libro.
     *
     * @param borrowed Nuevo estado de préstamo.
     */
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}
