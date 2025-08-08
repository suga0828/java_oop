package com.example.java_oop.university_library.model;

import java.util.Date;

/**
 * Representa un préstamo de libro en el sistema de biblioteca universitaria.
 */
public class Loan {
    private String number;
    private Date loanDate;
    private Date returnDate;
    private Student student;
    private Book book;

    /**
     * Crea una nueva instancia de Préstamo.
     * 
     * @param number Número del préstamo.
     * @param loanDate Fecha en que se realizó el préstamo.
     * @param returnDate Fecha esperada de devolución.
     * @param student Estudiante que realizó el préstamo.
     * @param book Libro que fue prestado.
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
