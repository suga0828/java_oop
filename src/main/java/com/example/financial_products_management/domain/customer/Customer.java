package com.example.financial_products_management.domain.customer;

/**
 * Representa los datos del titular de un producto financiero.
 * Incluye información de identificación, nombres, apellidos y teléfono.
 */
public class Customer {
    private final String identification;
    private final String firstName;
    private final String lastName;
    private final String phone;

    /**
     * Crea una nueva instancia de Cliente.
     *
     * @param identification Número de identificación del cliente
     * @param firstName      Nombres del cliente
     * @param lastName       Apellidos del cliente
     * @param phone          Número de teléfono del cliente
     */
    public Customer(String identification, String firstName, String lastName, String phone) {
        this.identification = identification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * Obtiene el número de identificación del cliente.
     *
     * @return Número de identificación
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Obtiene los nombres del cliente.
     *
     * @return Nombres del cliente
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Obtiene los apellidos del cliente.
     *
     * @return Apellidos del cliente
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return Número de teléfono
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Obtiene el nombre completo del cliente.
     *
     * @return Nombre completo (nombres + apellidos)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return String.format("ID: %s; Nombre: %s; Teléfono: %s", 
                identification, getFullName(), phone);
    }
}
