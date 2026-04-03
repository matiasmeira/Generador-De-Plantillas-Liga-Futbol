package com.matiasmeira.generador_plantillas.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {

    public record Entrada(
        @NotBlank(message = "El username es obligatorio") 
        String username,
        
        @NotBlank(message = "La contraseña es obligatoria") 
        String password,
        
        String rol
    ) {}

    public record Salida(
        Long id,
        String username,
        String rol
    ) {}
}