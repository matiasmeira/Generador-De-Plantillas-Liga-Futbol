package com.matiasmeira.generador_plantillas.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class EquipoDTO {
    
    public record Entrada(
        @NotBlank(message = "El nombre del equipo es obligatorio") 
        String nombre
    ) {}

    public record Salida(
        Long id,
        String nombre,
        UsuarioDTO.Salida usuarioDueno,
        List<JugadorDTO.Salida> jugadores
    ) {}

    public record Lite(
        Long id,
        String nombre,
        UsuarioDTO.Salida usuarioDueno
    ) {}
}