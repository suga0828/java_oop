package com.example.university_library.model;

import java.util.Date;

/**
 * Representa un préstamo de libro en el sistema de biblioteca universitaria.
 */
public class Loan {
    private final String number;
    private final Date loanDate;
    private final Date returnDate;
    private final Student student;
    private final Book book;

    /**
     * Crea una nueva instancia de Préstamo.
     *
     * @param number     Número del préstamo.
     * @param loanDate   Fecha en que se realizó el préstamo.
     * @param returnDate Fecha esperada de devolución.
     * @param student    Estudiante que realizó el préstamo.
     * @param book       Libro que fue prestado.
     */
    public Loan(String number, Date loanDate, Date returnDate, Student student, Book book) {
        this.number = number;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.student = student;
        this.book = book;
        this.book.setBorrowed(true);
    }

    /**
     * @return El número del préstamo.
     */
    public String getNumber() {
        return number;
    }
}
