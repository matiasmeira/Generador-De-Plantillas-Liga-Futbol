package com.matiasmeira.generador_plantillas.dto;

public record AuthResponse(
        String token,
        UsuarioDTO.Salida usuario
) {}
