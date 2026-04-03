package com.matiasmeira.generador_plantillas.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JugadorDTO {
    
    public record Entrada(
        @NotBlank(message = "El nombre es obligatorio") 
        String nombre,
        
        @NotBlank(message = "El apellido es obligatorio") 
        String apellido,
        
        @NotNull(message = "La fecha de nacimiento es obligatoria") 
        LocalDate fechaNacimiento,
        
        @NotBlank(message = "El DNI es obligatorio") 
        String dni,
        
        @NotNull(message = "El ID del equipo es obligatorio") 
        Long equipoId
    ) {}

    public record Salida(
        Long id,
        String nombre,
        String apellido,
        LocalDate fechaNacimiento,
        String dni,
        Long equipoId
    ) {}
}