package com.matiasmeira.generador_plantillas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.generador_plantillas.model.Equipo;
import com.matiasmeira.generador_plantillas.service.EquipoService;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "http://localhost:5173")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @PostMapping
    public Equipo crear(@RequestBody Equipo equipo, @RequestParam Long usuarioId) {
        return equipoService.guardar(equipo, usuarioId);
    }

    @GetMapping
    public List<Equipo> listar() {
        return equipoService.obtenerTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
        @PathVariable Long id, 
        @RequestBody Equipo equipo,
        @RequestHeader("X-User-Id") Long usuarioId,
        @RequestHeader("X-User-Role") String rol
    ) {
        // El controller delega toda la responsabilidad al service
        Equipo actualizado = equipoService.actualizarConSeguridad(id, equipo, usuarioId, rol);
        return ResponseEntity.ok(actualizado);
    }
}
