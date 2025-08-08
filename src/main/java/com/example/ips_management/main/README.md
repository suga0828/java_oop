# Sistema de Gestión de Citas Médicas (IPS)

## Descripción

Sistema para la gestión de citas médicas que permite a los pacientes agendar citas con especialistas en diferentes
centros médicos, desarrollado en Java siguiendo los principios de la Programación Orientada a Objetos.

## Características

- Gestión de pacientes, doctores y centros médicos
- Agendamiento de citas
- Búsqueda de cita por número
- Generación automática de números de citas

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
        +getId() : String
        +getName() : String
        +getCity() : City
        +getAddress() : String
        +getPhone() : String
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
    Patient "1" --> "1" City : vive en
    MedicalCenter "1" --> "1" City : ubicada en
    Patient "1" --> "*" Appointment : tiene
    Doctor "1" --> "*" Appointment : atiende
    MedicalCenter "1" --> "*" Appointment : tiene
    Ips "1" --> "*" Patient : administra
    Ips "1" --> "*" Doctor : administra
    Ips "1" --> "*" MedicalCenter : administra
    Ips "1" --> "*" Appointment : agenda

```

## Estructura del Proyecto

```
java_oop/
├── src/
│   └── com/
│       └── example/
│           └── ips_management/
│               ├── model/       # Clases del modelo de dominio
│               │   ├── Appointment.java
│               │   ├── City.java
│               │   ├── Doctor.java
│               │   ├── Ips.java
│               │   ├── MedicalCenter.java
│               │   └── Patient.java
│               └── main/        # Clase principal de la aplicación
│                   └── IpsManagementApp.java
└── README.md
```

## Compilación y Ejecución

### Requisitos

- Java 8 o superior

### Compilación

```bash
# Navegar al directorio del proyecto
cd /ruta/a/java_oop

# Compilar el proyecto
javac -d out $(find src/main/java -name "*.java")
```

### Ejecución

```bash
# Desde el directorio del proyecto
java -cp out com.example.ips_management.main.IpsManagementApp
```

## Uso

El sistema demuestra las siguientes funcionalidades:

1. Creación de ciudades, centros médicos, doctores y pacientes
2. Registro de citas médicas
3. Búsqueda de citas por número
4. Visualización de información detallada de las citas

El sistema incluye una demostración con datos de ejemplo que muestra el flujo completo de:

- Creación de pacientes, doctores y centros médicos
- Agendamiento de citas
- Búsqueda y visualización de citas existentes

## Diagrama de Clases

## Notas Adicionales

- El sistema genera automáticamente números de cita secuenciales.
- Las citas pueden tener diferentes estados: "programada", "completada", "cancelada".
- Se incluye un conjunto de datos de ejemplo para pruebas con doctores, pacientes y centros médicos.
