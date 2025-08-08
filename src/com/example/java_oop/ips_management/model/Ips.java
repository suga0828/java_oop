package com.example.java_oop.ips_management.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una Institución Prestadora de Servicios de Salud (IPS)
 * que gestiona pacientes, doctores, centros médicos y citas.
 */
public class Ips {
    private final List<Patient> patients = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<MedicalCenter> medicalCenters = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();
    private int nextAppointmentNumber = 1;

    /**
     * Genera el siguiente número de préstamo.
     *
     * @return El siguiente número de préstamo como cadena formateada.
     */
    public String getNextAppointmentNumber() {
        return String.format("A%03d", nextAppointmentNumber++);
    }


    /**
     * @return Lista de todos los pacientes registrados.
     */
    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    /**
     * @return Lista de todos los doctores registrados.
     */
    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    /**
     * @return Lista de todos los centros médicos registrados.
     */
    public List<MedicalCenter> getMedicalCenters() {
        return new ArrayList<>(medicalCenters);
    }

    /**
     * @return Lista de todas las citas agendadas.
     */
    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    /**
     * Registra un nuevo paciente en la IPS.
     *
     * @param patient Paciente a registrar.
     */
    public void registerPatient(Patient patient) {
        patients.add(patient);
    }

    /**
     * Registra un nuevo doctor en la IPS.
     *
     * @param doctor Doctor a registrar.
     */
    public void registerDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    /**
     * Registra un nuevo centro médico en la IPS.
     *
     * @param medicalCenter Centro médico a registrar.
     */
    public void registerMedicalCenter(MedicalCenter medicalCenter) {
        medicalCenters.add(medicalCenter);
    }

    /**
     * Registra una nueva cita en la IPS.
     *
     * @param appointment Cita a registrar.
     */
    public void registerAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Busca una cita por su number.
     *
     * @param number número de la cita a buscar.
     * @return La cita con el number dado, o null si no se encuentra.
     */
    public Appointment findAppointmentByNumber(String number) {
        for (Appointment appointment : appointments) {
            if (appointment.getNumber().equals(number)) {
                return appointment;
            }
        }
        return null;
    }
}
