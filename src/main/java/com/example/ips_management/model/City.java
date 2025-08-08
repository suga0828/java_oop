package com.example.ips_management.model;

/**
 * Representa una ciudad en el sistema de citas médicas.
 */
public class City {
    private String code;
    private String name;

    /**
     * Crea una nueva instancia de Ciudad.
     *
     * @param code El código de la ciudad.
     * @param name El nombre de la ciudad.
     */
    public City(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * @return Nombre de la ciudad
     */
    public String getName() {
        return name;
    }
}
