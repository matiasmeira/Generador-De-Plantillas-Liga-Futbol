package com.matiasmeira.generador_plantillas.controller;

import com.matiasmeira.generador_plantillas.model.Jugador;
import com.matiasmeira.generador_plantillas.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;

    @PostMapping
    public Jugador crear(@RequestBody Jugador jugador, @RequestParam Long equipoId) {
        return jugadorService.guardar(jugador, equipoId);
    }

    @GetMapping("/equipo/{equipoId}")
    public List<Jugador> listarPorEquipo(@PathVariable Long equipoId) {
        return jugadorService.obtenerPorEquipo(equipoId);
    }
}
