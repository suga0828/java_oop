# Modelo de Dominio - Sistema de Citas Médicas

## Descripción

Este documento describe las clases del modelo de dominio del Sistema de Gestión de Citas Médicas, que representan las
entidades principales y sus relaciones.

## Entidades Principales

### 1. City (Ciudad)

Representa una ciudad donde se ubican los centros médicos.

- **Atributos:**
  - `code`: Código único de la ciudad (ej: "BOG")
  - `name`: Nombre de la ciudad (ej: "Bogotá")

### 2. MedicalCenter (Centro Médico)

Representa un establecimiento de salud donde se atienden las citas.

- **Atributos:**
  - `code`: Código único del centro médico
  - `city`: Ciudad donde se ubica el centro
  - `address`: Dirección física del centro

### 3. Doctor (Médico)

Representa a un profesional de la salud que atiende citas.

- **Atributos:**
  - `code`: Código único del médico
  - `firstName`: Nombre del médico
  - `lastName`: Apellido del médico
  - `specialization`: Especialidad médica
  - `specializationYear`: Año de especialización

### 4. Patient (Paciente)

Representa un paciente que solicita citas médicas.

- **Atributos:**
  - `id`: Identificación única
  - `name`: Nombre completo del paciente
  - `city`: Ciudad de residencia
  - `address`: Dirección del paciente
  - `phone`: Número de teléfono
  - `appointments`: Lista de citas del paciente

### 5. Appointment (Cita Médica)

Representa una cita médica programada.

- **Atributos:**
  - `number`: Número único de cita
  - `patient`: Paciente de la cita
  - `doctor`: Médico asignado
  - `date`: Fecha y hora de la cita
  - `status`: Estado actual (programada/completada/cancelada)
  - `medicalCenter`: Centro médico donde se realizará

### 6. Ips (Institución Prestadora de Servicios de Salud)

Clase principal que actúa como el núcleo del sistema de gestión de citas médicas, coordinando todas las operaciones
entre las diferentes entidades.

- **Atributos:**
  - `patients`: Lista de todos los pacientes registrados en el sistema
  - `doctors`: Lista de todos los médicos disponibles
  - `medicalCenters`: Lista de centros médicos afiliados
  - `appointments`: Registro histórico de todas las citas agendadas
  - `appointmentCounter`: Contador secuencial para generar números de cita únicos

## Relaciones

- Un `MedicalCenter` pertenece a una `City` (1:1)
- Un `Patient` tiene una `City` de residencia (1:1)
- Un `Patient` puede tener múltiples `Appointment` (1:N)
- Un `Doctor` puede tener múltiples `Appointment` (1:N)
- Un `MedicalCenter` puede tener múltiples `Appointment` (1:N)
- Cada `Appointment` está asociada a un `Patient`, un `Doctor` y un `MedicalCenter` (N:1)

## Diagrama de Clases

```mermaid
classDiagram
    note for City "Ciudad"
    class City {
        -String code
        -String name
        +City(String code, String name)
        +String getName()
    }

    note for MedicalCenter "Centro Médico"
    class MedicalCenter {
        -String code
        -City city
        -String address
        +MedicalCenter(String code, City city, String address)
        +String getCode()
        +City getCity()
        +String getAddress()
    }

    note for Doctor "Doctor"
    class Doctor {
        -String code
        -String firstName
        -String lastName
        -String specialization
        -int specializationYear
        +Doctor(String code, String firstName, String lastName, String specialization, int specializationYear)
        +String getCode()
        +String getFirstName()
        +String getLastName()
        +String getSpecialization()
        +int getSpecializationYear()
    }

    note for Appointment "Cita"
    class Appointment {
        -String number
        -Patient patient
        -Doctor doctor
        -Date date
        -String status
        -MedicalCenter medicalCenter
        +Appointment(String number, Patient patient, Doctor doctor, Date date, String status, MedicalCenter medicalCenter)
        +String getNumber()
        +Patient getPatient()
        +Doctor getDoctor()
        +String getStatus()
        +void setStatus(String)
        +LocalDate getDay()
        +LocalTime getTime()
        +MedicalCenter getMedicalCenter()
    }

    note for Patient "Paciente"
    class Patient {
        -String id
        -String name
        -City city
        -String address
        -String phone
        -List<Appointment> appointments
        +Patient(String id, String name, City city, String address, String phone)
        +String getId()
        +String getName()
        +City getCity()
        +String getAddress()
        +String getPhone()
        +List<Appointment> getAppointments()
    }

    note for Ips "Sistema de Gestión"
    class Ips {
        -List<Patient> patients
        -List<Doctor> doctors
        -List<MedicalCenter> medicalCenters
        -List<Appointment> appointments
        -int nextAppointmentNumber
        +List<Patient> getPatients()
        +List<Doctor> getDoctors()
        +List<MedicalCenter> getMedicalCenters()
        +List<Appointment> getAppointments()
        +String getNextAppointmentNumber()
        +void registerPatient(Patient)
        +void registerDoctor(Doctor)
        +void registerMedicalCenter(MedicalCenter)
        +void registerAppointment(Appointment)
        +Appointment findAppointmentByNumber(String)
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
