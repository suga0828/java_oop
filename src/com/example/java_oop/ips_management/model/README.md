# Modelo de Dominio - Sistema de Citas Médicas

## Descripción
Este documento describe las clases del modelo de dominio del Sistema de Gestión de Citas Médicas, que representan las entidades principales y sus relaciones.

## Entidades Principales

### 1. City (Ciudad)
Representa una ubicación geográfica donde se encuentran los centros médicos.
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
  - `firstName`: Primer nombre
  - `lastName`: Apellido
  - `specialization`: Especialidad médica
  - `specializationYear`: Año de especialización

### 4. Patient (Paciente)
Representa a un paciente que solicita citas médicas.
- **Atributos:**
  - `id`: Identificación única
  - `firstName`: Primer nombre
  - `lastName`: Apellido
  - `email`: Correo electrónico
  - `phone`: Número de teléfono

### 5. Appointment (Cita Médica)
Representa una cita médica programada.
- **Atributos:**
  - `number`: Número único de cita
  - `date`: Fecha y hora de la cita
  - `status`: Estado actual (programada/completada/cancelada)
  - `patient`: Paciente de la cita
  - `doctor`: Médico asignado
  - `medicalCenter`: Centro médico donde se realizará

## Relaciones
- Un `MedicalCenter` pertenece a una `City` (1:1)
- Un `Appointment` está asociado a un `Patient` (1:1)
- Un `Appointment` está asociado a un `Doctor` (1:1)
- Un `Appointment` está asociado a un `MedicalCenter` (1:1)

## Diagrama de Clases

```mermaid
classDiagram
    City ||--o{ MedicalCenter : contiene
    MedicalCenter ||--o{ Appointment : alberga
    Doctor ||--o{ Appointment : atiende
    Patient ||--o{ Appointment : tiene
    
    class City {
        -code: String
        -name: String
        +getCode()
        +getName()
    }
    
    class MedicalCenter {
        -code: String
        -city: City
        -address: String
        +getCode()
        +getCity()
        +getAddress()
    }
    
    class Doctor {
        -code: String
        -firstName: String
        -lastName: String
        -specialization: String
        -specializationYear: int
        +getFirstName()
        +getLastName()
        +getSpecialization()
    }
    
    class Patient {
        -id: String
        -firstName: String
        -lastName: String
        -email: String
        -phone: String
        +getId()
        +getFullName()
    }
    
    class Appointment {
        -number: String
        -date: Date
        -status: String
        -patient: Patient
        -doctor: Doctor
        -medicalCenter: MedicalCenter
        +getNumber()
        +getDate()
        +getStatus()
        +setStatus()
    }
```

## Convenciones de Código
- Todas las clases están en el paquete `co.edu.umng.java_oop.ips_management.model`
- Los atributos son privados y se acceden a través de métodos getter
- Los constructores validan los parámetros requeridos
- La documentación está en español siguiendo el estándar Javadoc
