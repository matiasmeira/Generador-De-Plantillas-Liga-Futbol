package com.matiasmeira.generador_plantillas.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JugadorDTO {
    
    @Data @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class Entrada {
        private String nombre;
        private String apellido;
        private LocalDate fechaNacimiento;
        private String dni;
        private Long equipoId;
    }

    @Data @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class Salida {
        private Long id;
        private String nombre;
        private String apellido;
        private LocalDate fechaNacimiento;
        private String dni;
        private Long equipoId;
    }
}
