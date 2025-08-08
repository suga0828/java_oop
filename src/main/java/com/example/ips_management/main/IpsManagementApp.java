package com.example.ips_management.main;

import com.example.ips_management.model.*;

import java.util.Date;

/**
 * Clase principal de la aplicación de Gestión de Citas Médicas.
 */
public class IpsManagementApp {
    /**
     * Registra un mensaje en el log con marca de tiempo.
     *
     * @param mensaje Mensaje a registrar.
     */
    protected static void log(String mensaje) {
        System.out.println("[" + new java.util.Date() + "] " + mensaje);
    }

    /**
     * Punto de entrada principal de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // Create cities
        City city1 = new City("BOG", "Bogotá");
        City city2 = new City("MED", "Medellín");

        // Create patients
        Patient patient1 = new Patient("1000000001", "Carlos", city1, "Calle 1 # 2 - 3", "3001234567");
        Patient patient2 = new Patient("1000000002", "Ana", city2, "Carrera 4 # 5 - 6", "3109876543");
        Patient patient3 = new Patient("1000000003", "Sandra", city1, "Diagonal 7 # 8 - 9", "3109876543");

        // Create doctors
        Doctor doctor1 = new Doctor("DOC-001", "Juan", "Pérez", "Cardiología", 2010);
        Doctor doctor2 = new Doctor("DOC-002", "María", "González", "Pediatría", 2015);

        // Create medical center
        MedicalCenter centro1 = new MedicalCenter("CM-001", city1, "Calle 123 #45-67");
        MedicalCenter centro2 = new MedicalCenter("CM-002", city2, "Carrera 89 #10-11");

        log("Inicializando IPS...\n");
        Ips ips = new Ips();

        // Add medical centers to IPS
        log("Agregando centros médicos a la IPS...\n");
        ips.registerMedicalCenter(centro1);
        ips.registerMedicalCenter(centro2);

        // Add patients to IPS
        log("Agregando pacientes a la IPS...\n");
        ips.registerPatient(patient1);
        ips.registerPatient(patient2);

        // Add Doctors to IPS
        log("Agregando doctores a la IPS...\n");
        ips.registerDoctor(doctor1);
        ips.registerDoctor(doctor2);


        // Generate appointments
        log("=== PROCESO DE AGENDAMIENTO DE CITAS ===");
        Date currentDate = new Date();
        Date appointmentDate = new Date(currentDate.getTime() + (7 * 24 * 60 * 60 * 1000)); // 7 days later

        // Create and add appointments
        Appointment appointment1 = new Appointment(ips.getNextAppointmentNumber(), patient1, doctor1, appointmentDate, "programada", centro1);
        ips.registerAppointment(appointment1);
        log("Cita registrada exitosamente: " + appointment1.getNumber());

        Appointment appointment2 = new Appointment(ips.getNextAppointmentNumber(), patient2, doctor2, appointmentDate, "programada", centro2);
        ips.registerAppointment(appointment2);
        log("Cita registrada exitosamente: " + appointment2.getNumber());

        Appointment appointment3 = new Appointment(ips.getNextAppointmentNumber(), patient3, doctor1, appointmentDate, "programada", centro1);
        ips.registerAppointment(appointment3);
        log("Cita registrada exitosamente: " + appointment3.getNumber());

        Appointment appointment4 = new Appointment(ips.getNextAppointmentNumber(), patient3, doctor2, appointmentDate, "programada", centro2);
        ips.registerAppointment(appointment4);
        log("Cita registrada exitosamente: " + appointment4.getNumber());

        // Show appointment3 data and related patient info
        log("=== RESUMEN DE CITAS AGENDADAS ===\n");
        Appointment foundAppointment = ips.findAppointmentByNumber(appointment3.getNumber());
        Patient foundPatient = foundAppointment.getPatient();
        System.out.println(String.format(
                """
                        Cita #%s
                          Paciente:
                            ID: %s
                            Nombre: %s
                            Ciudad: %s
                            Dirección: %s
                            Teléfono: %s
                          Doctor:
                            ID: %s
                            Nombre: %s
                            Apellido: %s
                            Especialización: %s
                            Año de especialización: %d
                          Fecha:
                            Día: %s
                            Hora: %s
                          Estado: %s
                          Centro Médico:
                            Ciudad: %s
                            Dirección: %s
                        """,
                foundAppointment.getNumber(),
                foundPatient.getId(),
                foundPatient.getName(),
                foundPatient.getCity().getName(),
                foundPatient.getAddress(),
                foundPatient.getPhone(),
                foundPatient.getId(),
                foundAppointment.getDoctor().getFirstName(),
                foundAppointment.getDoctor().getLastName(),
                foundAppointment.getDoctor().getSpecialization(),
                foundAppointment.getDoctor().getSpecializationYear(),
                foundAppointment.getDay(),
                foundAppointment.getTime(),
                foundAppointment.getStatus(),
                foundAppointment.getMedicalCenter().getCity().getName(),
                foundAppointment.getMedicalCenter().getAddress()
        ));


    }
}
