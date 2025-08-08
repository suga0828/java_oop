package com.example.ips_management.model;

/**
 * Representa un médico en el sistema de proveedores de atención médica.
 */
public class Doctor {
    private String code;
    private String firstName;
    private String lastName;
    private String specialization;
    private int specializationYear;

    /**
     * Crea una nueva instancia de Doctor.
     *
     * @param code               Código único del médico.
     * @param firstName          Primer nombre del médico.
     * @param lastName           Apellido del médico.
     * @param specialization     Especialización médica del doctor.
     * @param specializationYear Año en que el médico completó la especialización.
     */
    public Doctor(String code, String firstName, String lastName,
                  String specialization, int specializationYear) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.specializationYear = specializationYear;
    }

    /**
     * @return El primer nombre del médico.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return El apellido del médico.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return La especialización médica del doctor.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * @return El código del médico.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return El año en que el médico completó la especialización.
     */
    public int getSpecializationYear() {
        return specializationYear;
    }
}
