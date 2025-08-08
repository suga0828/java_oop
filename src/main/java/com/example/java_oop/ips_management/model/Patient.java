package com.example.java_oop.ips_management.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un paciente en el sistema de citas médicas.
 */
public class Patient {
    private String id;
    private String name;
    private City city;
    private String address;
    private String phone;
    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Crea una nueva instancia de Paciente.
     *
     * @param id           Número de identificación del paciente.
     * @param name         Nombre del paciente.
     * @param city         Ciudad del paciente.
     * @param address      Dirección del paciente.
     * @param phone        Número de teléfono del paciente.
     * @param appointments Citas del paciente.
     */
    public Patient(String id, String name, City city, String address, String phone) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    /**
     * @return Número de identificación del paciente.
     */
    public String getId() {
        return this.id;
    }

    /**
     * @return Nombre del paciente.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Ciudad del paciente.
     */
    public City getCity() {
        return city;
    }

    /**
     * @return Dirección del paciente.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return Número de teléfono del paciente.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return Citas del paciente.
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }
}