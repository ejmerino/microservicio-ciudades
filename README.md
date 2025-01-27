# Examen de Segundo Parcial - Aplicaciones Distribuidas

## Descripción

Este repositorio contiene dos microservicios desarrollados como parte del examen del segundo parcial en la materia de Aplicaciones Distribuidas. Los microservicios están diseñados para gestionar información sobre **ciudades** y **turistas**. Ambos microservicios están comunicados entre sí y utilizan una base de datos **MySQL** para el almacenamiento y gestión de los datos.

### Microservicios:
1. **Microservicio de Ciudad**: Este microservicio gestiona información relacionada con las ciudades.
2. **Microservicio de Turistas**: Este microservicio gestiona los datos de los turistas.

## Tecnologías Utilizadas

- **Spring Boot**: Para el desarrollo de los microservicios.
- **MySQL**: Base de datos para almacenar los datos de ciudades y turistas.
- **JPA (Java Persistence API)**: Para la gestión de la persistencia en la base de datos.
- **RestTemplate**: Para la comunicación entre los microservicios.

## Requisitos

- **Java 11 o superior**
- **MySQL** instalado y en funcionamiento
- **Maven** para la gestión de dependencias

## Configuración

### 1. Clonar el Repositorio
Primero, clona este repositorio en tu máquina local:
```bash
git clone https://github.com/ejmerino/microservicio-examen.git
cd microservicio-examen
