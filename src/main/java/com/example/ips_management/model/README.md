# Modelo de Dominio - Sistema de Citas Médicas

## Descripción

Este documento describe las clases del modelo de dominio del Sistema de Gestión de Citas Médicas, que representan las
entidades principales y sus relaciones.

## Diagrama de Clases

```mermaid
classDiagram
    note for City "Ciudad"
    class City {
        -code : String
        -name : String
        +City(String code, String name)
        +getName() : String
    }

    note for MedicalCenter "Centro Médico"
    class MedicalCenter {
        -code : String
        -city : City
        -address : String
        +MedicalCenter(String code, City city, String address)
        +getCity() : City
        +getAddress() : String
    }

    note for Doctor "Doctor"
    class Doctor {
        -code : String
        -firstName : String
        -lastName : String
        -specialization : String
        -specializationYear : int
        +Doctor(String code, String firstName, String lastName, String specialization, int specializationYear)
        +getFirstName() : String
        +getLastName() : String
        +getSpecialization() : String
        +getSpecializationYear() : int
    }

    note for Appointment "Cita"
    class Appointment {
        -number : String
        -patient : Patient
        -doctor : Doctor
        -date : Date
        -status : String
        -medicalCenter : MedicalCenter
        +Appointment(String number, Patient patient, Doctor doctor, Date date, String status, MedicalCenter medicalCenter)
        +getNumber() : String
        +getPatient() : Patient
        +getStatus() : String
        +setStatus(String): void
        +getDoctor() : Doctor
        +getDay() : LocalDate
        +getTime() : LocalTime
        +getMedicalCenter() : MedicalCenter
    }

    note for Patient "Paciente"
    class Patient {
        -id : String
        -name : String
        -city : City
        -address : String
        -phone : String
        -appointments : Appointment[]
        +Patient(String id, String name, City city, String address, String phone)
        +String getId()
        +String getName()
        +City getCity()
        +String getAddress()
        +String getPhone()
    }

    note for Ips "Institución Prestadora de Servicios de Salud"
    class Ips {
        -nextAppointmentNumber : int
        -patients : Patient[]
        -doctors : Doctor[]
        -medicalCenters : MedicalCenter[]
        -appointments : Appointment[]
        +getNextAppointmentNumber() : String
        +getPatients() : Patient[]
        +getDoctors() : Doctor[]
        +getMedicalCenters() : MedicalCenter[]
        +getAppointments() : Appointment[]
        +registerPatient(Patient): void
        +registerDoctor(Doctor): void
        +registerMedicalCenter(MedicalCenter): void
        +registerAppointment(Appointment): void
        +findAppointmentByNumber(String): Appointment
    }

    %% Relationships
    Patient "1" -- "1" City : resides in >
    MedicalCenter "1" -- "1" City : located in >
    Patient "1" -- "*" Appointment : has >
    Doctor "1" -- "*" Appointment : serves >
    MedicalCenter "1" -- "*" Appointment : hosts >
    Ips "1" -- "*" Patient : manages >
    Ips "1" -- "*" Doctor : manages >
    Ips "1" -- "*" MedicalCenter : manages >
    Ips "1" -- "*" Appointment : manages >

```
