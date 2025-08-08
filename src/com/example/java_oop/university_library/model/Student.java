package com.example.java_oop.university_library.model;

/**
 * Representa un estudiante en el sistema de biblioteca universitaria.
 */
public class Student {
    private String id;
    private String name;
    private String program;

    /**
     * Crea una nueva instancia de Estudiante.
     *
     * @param id      Identificación del estudiante.
     * @param name    Nombre del estudiante.
     * @param program Programa académico del estudiante.
     */
    public Student(String id, String name, String program) {
        this.id = id;
        this.name = name;
        this.program = program;
    }
}
