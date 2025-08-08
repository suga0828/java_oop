package com.example.java_oop.university_library.model;

/**
 * Representa una editorial en el sistema de biblioteca universitaria.
 */
public class Publisher {
    private String code;
    private String name;
    private Country country;

    /**
     * Crea una nueva instancia de Editorial.
     * 
     * @param code Código de la editorial.
     * @param name Nombre de la editorial.
     * @param country País de la editorial.
     */
    public Publisher(String code, String name, Country country) {
        this.code = code;
        this.name = name;
        this.country = country;
    }
}
