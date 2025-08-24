package com.example.university_library.model;

/**
 * Representa un autor en el sistema de biblioteca universitaria.
 */
public class Author {
    private final String name;
    private final Country country;

    /**
     * Crea una nueva instancia de Autor.
     *
     * @param name    Nombre del autor.
     * @param country País de origen del autor.
     */
    public Author(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    /**
     * @return El nombre del autor.
     */
    public String getName() {
        return name;
    }
}
