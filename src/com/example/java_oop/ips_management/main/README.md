# Sistema de Gestión de Citas Médicas

## Descripción
Sistema para la gestión de citas médicas que permite a los pacientes agendar citas con especialistas en diferentes centros médicos, desarrollado en Java siguiendo los principios de la Programación Orientada a Objetos.

## Características
- Gestión de pacientes, doctores y centros médicos
- Agendamiento de citas con seguimiento de estado
- Búsqueda de especialistas por especialidad
- Gestión de ubicaciones geográficas (ciudades)

## Estructura del Proyecto
```
medical-appointments/
├── src/
│   └── main/
│       └── java/
│           └── co/
│               └── edu/
│                   └── umng/
│                       └── poo/
│                           └── medical_appointments/
│                               ├── model/    # Clases del modelo de dominio
│                               └── main/     # Clase principal de la aplicación
└── README.md
```

## Compilación y Ejecución

### Requisitos
- Java 8 o superior
- Maven (opcional, pero recomendado)

### Compilación
```bash
# Navegar al directorio del proyecto
cd /ruta/a/medical-appointments

# Compilar el proyecto
javac -d target/classes -sourcepath src/main/java src/main/java/co/edu/umng/poo/medical_appointments/main/HealthcareProviderApp.java
```

### Ejecución
```bash
# Desde el directorio del proyecto
java -cp target/classes main.ips_management.com.example.java_oop.IpsManagementApp
```

## Uso
El sistema inicia mostrando un menú de opciones para gestionar las citas médicas, incluyendo:
- Registro de nuevos pacientes
- Registro de médicos
- Agendamiento de citas
- Consulta de citas existentes
- Gestión de centros médicos

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
        -firstName: String
        -lastName: String
        -email: String
        -phone: String
        +Patient(id: String, firstName: String, lastName: String, 
                email: String, phone: String)
        +getId() String
        +getFirstName() String
        +getLastName() String
        +getEmail() String
        +getPhone() String
    }

    class Appointment {
        -number: String
        -date: Date
        -status: String
        -patient: Patient
        -doctor: Doctor
        -medicalCenter: MedicalCenter
        +Appointment(number: String, date: Date, status: String,
                    patient: Patient, doctor: Doctor, medicalCenter: MedicalCenter)
        +getNumber() String
        +getDate() Date
        +getStatus() String
        +getPatient() Patient
        +getDoctor() Doctor
        +getMedicalCenter() MedicalCenter
        +setStatus(newStatus: String) void
    }

    MedicalCenter "1" -- "1" City : está en
    Appointment "*" -- "1" Patient : tiene
    Appointment "*" -- "1" Doctor : con
    Appointment "*" -- "1" MedicalCenter : en
```

## Notas Adicionales
- El sistema genera automáticamente números de cita secuenciales.
- Las citas pueden tener diferentes estados: "programada", "completada", "cancelada".
- Se incluye un conjunto de datos de ejemplo para pruebas con doctores, pacientes y centros médicos.
