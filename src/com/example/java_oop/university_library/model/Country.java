package com.example.java_oop.university_library.model;

/**
 * Representa un país en el sistema de biblioteca universitaria.
 */
public class Country {
    private String code;
    private String name;

    /**
     * Crea una nueva instancia de País.
     * 
     * @param code Código del país.
     * @param name Nombre del país.
     */
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
