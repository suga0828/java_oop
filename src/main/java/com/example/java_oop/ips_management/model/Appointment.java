package com.example.java_oop.ips_management.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Representa una cita médica en el sistema.
 */
public class Appointment {
    private String number;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String status; // "scheduled", "completed", "cancelled"
    private MedicalCenter medicalCenter;

    /**
     * Crea una nueva instancia de Cita Médica.
     *
     * @param number        Número único de la cita.
     * @param patient       Paciente que asiste a la cita.
     * @param doctor        Médico que atenderá la cita.
     * @param date          Fecha y hora de la cita.
     * @param status        Estado actual de la cita.
     * @param medicalCenter Centro médico donde se realizará la cita.
     */
    public Appointment(String number, Patient patient, Doctor doctor, Date date, String status,
                       MedicalCenter medicalCenter) {
        this.number = number;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.status = status;
        this.medicalCenter = medicalCenter;
    }

    /**
     * @return El número de la cita.
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return El paciente de la cita.
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @return El estado de la cita.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Cambia el estado de la cita.
     *
     * @param newStatus Nuevo estado para la cita.
     */
    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    /**
     * @return El estado de la cita.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @return El día de la cita.
     */
    public LocalDate getDay() {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * @return La hora de la cita.
     */
    public LocalTime getTime() {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalTime();
    }

    /**
     * @return El centro médico de la cita.
     */
    public MedicalCenter getMedicalCenter() {
        return medicalCenter;
    }
}
