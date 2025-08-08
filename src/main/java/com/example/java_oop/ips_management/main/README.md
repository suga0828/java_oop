# Sistema de Gestión de Citas Médicas (IPS)

## Descripción

Sistema para la gestión de citas médicas que permite a los pacientes agendar citas con especialistas en diferentes
centros médicos, desarrollado en Java siguiendo los principios de la Programación Orientada a Objetos.

## Características

- Gestión de pacientes, doctores y centros médicos
- Agendamiento de citas con seguimiento de estado
- Búsqueda de especialistas por especialidad
- Gestión de ubicaciones geográficas (ciudades)

## Estructura del Proyecto

```
java_oop/
├── src/
│   └── com/
│       └── example/
│           └── java_oop/
│               └── ips_management/
│                   ├── model/       # Clases del modelo de dominio
│                   │   ├── Appointment.java
│                   │   ├── City.java
│                   │   ├── Doctor.java
│                   │   ├── Ips.java
│                   │   ├── MedicalCenter.java
│                   │   └── Patient.java
│                   └── main/        # Clase principal de la aplicación
│                       └── IpsManagementApp.java
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
javac -d target/classes -sourcepath src/main/java src/main/java/com/example/java_oop/ips_management/main/IpsManagementApp.java
```

### Ejecución

```bash
# Desde el directorio del proyecto
java -cp target/classes main.ips_management.com.example.java_oop.IpsManagementApp
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

```mermaid
classDiagram
    class City {
        -code: String
        -name: String
        +City(code: String, name: String)
        +getCode() String
        +getName() String
    }

    class MedicalCenter {
        -code: String
        -city: City
        -address: String
        +MedicalCenter(code: String, city: City, address: String)
        +getCode() String
        +getCity() City
        +getAddress() String
    }

    class Doctor {
        -code: String
        -firstName: String
        -lastName: String
        -specialization: String
        -specializationYear: int
        +Doctor(code: String, firstName: String, lastName: String,
               specialization: String, specializationYear: int)
        +getFirstName() String
        +getLastName() String
        +getSpecialization() String
        +getCode() String
        +getSpecializationYear() int
    }

    class Patient {
        -id: String
        -name: String
        -city: City
        -address: String
        -phone: String
        -appointments: List~Appointment~
        +Patient(id: String, name: String, city: City, address: String, phone: String)
        +getId() String
        +getName() String
        +getCity() City
        +getAddress() String
        +getPhone() String
        +getAppointments() List~Appointment~
    }

    class Appointment {
        -number: String
        -patient: Patient
        -doctor: Doctor
        -date: Date
        -status: String
        -medicalCenter: MedicalCenter
        +Appointment(number: String, patient: Patient, doctor: Doctor,
                    date: Date, status: String, medicalCenter: MedicalCenter)
        +getNumber() String
        +getPatient() Patient
        +getStatus() String
        +setStatus(newStatus: String) void
        +getDoctor() Doctor
        +getDay() LocalDate
        +getTime() LocalTime
        +getMedicalCenter() MedicalCenter
    }

    class Ips {
        -appointmentCounter: int
        -patients: List~Patient~
        -doctors: List~Doctor~
        -medicalCenters: List~MedicalCenter~
        -appointments: List~Appointment~
        +registerPatient(patient: Patient) void
        +registerDoctor(doctor: Doctor) void
        +registerMedicalCenter(center: MedicalCenter) void
        +registerAppointment(appointment: Appointment) void
        +findAppointmentByNumber(number: String) Appointment
        +getNextAppointmentNumber() String
    }

    Patient "1" -- "0..*" Appointment : has >
    Doctor "1" -- "0..*" Appointment : attends >
    MedicalCenter "1" -- "0..*" Appointment : hosts >
    City "1" -- "1" MedicalCenter : located in >
    City "1" -- "0..*" Patient : residence of >
    Ips "1" -- "1..*" Patient : manages >
    Ips "1" -- "1..*" Doctor : manages >
    Ips "1" -- "1..*" MedicalCenter : manages >
    Ips "1" -- "1..*" Appointment : manages >
```

## Notas Adicionales

- El sistema genera automáticamente números de cita secuenciales.
- Las citas pueden tener diferentes estados: "programada", "completada", "cancelada".
- Se incluye un conjunto de datos de ejemplo para pruebas con doctores, pacientes y centros médicos.
