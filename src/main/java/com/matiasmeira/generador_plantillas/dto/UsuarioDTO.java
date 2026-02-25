package com.matiasmeira.generador_plantillas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UsuarioDTO {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class Entrada {
        private String username;
        private String password;
        private String rol;
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class Salida {
        private Long id;
        private String username;
        private String rol;
    }
}
