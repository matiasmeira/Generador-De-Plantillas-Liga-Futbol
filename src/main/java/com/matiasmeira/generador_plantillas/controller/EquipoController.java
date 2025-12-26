package com.matiasmeira.generador_plantillas.controller;

import com.matiasmeira.generador_plantillas.model.Equipo;
import com.matiasmeira.generador_plantillas.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
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
}
