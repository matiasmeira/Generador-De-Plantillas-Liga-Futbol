package com.matiasmeira.generador_plantillas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.generador_plantillas.dto.EquipoDTO;
import com.matiasmeira.generador_plantillas.model.Equipo;
import com.matiasmeira.generador_plantillas.service.EquipoService;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody EquipoDTO.Entrada equipo, @RequestParam Long usuarioId) {
        EquipoDTO.Salida nuevoEquipo = equipoService.guardar(equipo, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEquipo);
    }

    @GetMapping
    public ResponseEntity<List<?>> listar(
        @RequestParam(value = "include", required = false) String include) {
            if ("players".equals(include)){
                return ResponseEntity.ok(equipoService.obtenerTodos());
            }
        return ResponseEntity.ok(equipoService.obtenerTodosLite());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
        @PathVariable Long id, 
        @RequestBody Equipo equipo,
        @RequestHeader("X-User-Id") Long usuarioId,
        @RequestHeader("X-User-Role") String rol
    ) {

        EquipoDTO.Salida actualizado = equipoService.actualizarConSeguridad(id, equipo, usuarioId, rol);
        return ResponseEntity.ok(actualizado);
    }
}
