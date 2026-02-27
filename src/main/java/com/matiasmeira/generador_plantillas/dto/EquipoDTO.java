package com.matiasmeira.generador_plantillas.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EquipoDTO {
    
    @Data @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class Entrada {
        private String nombre;

    }

    @Data @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class Salida {
        private Long id;
        private String nombre;
        private UsuarioDTO.Salida usuarioDueno;
        private List<JugadorDTO.Salida> jugadores;
    }

    @Data @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class Lite {
        private Long id;
        private String nombre;
        private UsuarioDTO.Salida usuarioDueno;
    }
}
