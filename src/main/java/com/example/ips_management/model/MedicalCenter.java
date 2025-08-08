package com.example.ips_management.model;

/**
 * Representa un centro médico en el sistema de proveedores de atención médica.
 */
public class MedicalCenter {
    private String code;
    private City city;
    private String address;

    /**
     * Crea una nueva instancia de Centro Médico.
     *
     * @param code    El código del centro médico.
     * @param city    La ciudad donde se encuentra el centro médico.
     * @param address La dirección del centro médico.
     */
    public MedicalCenter(String code, City city, String address) {
        this.code = code;
        this.city = city;
        this.address = address;
    }

    /**
     * @return La ciudad del centro médico.
     */
    public City getCity() {
        return city;
    }

    /**
     * @return La dirección del centro médico.
     */
    public String getAddress() {
        return address;
    }
}
