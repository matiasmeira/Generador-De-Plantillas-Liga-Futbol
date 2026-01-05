package com.matiasmeira.generador_plantillas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.generador_plantillas.model.Jugador;
import com.matiasmeira.generador_plantillas.service.JugadorService;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;

    @PostMapping
    public Jugador crear(@RequestBody Jugador jugador, @RequestParam Long equipoId) {
        return jugadorService.guardar(jugador, equipoId);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        jugadorService.eliminar(id);
    }

    @GetMapping("/equipo/{equipoId}")
    public List<Jugador> listarPorEquipo(@PathVariable Long equipoId) {
        return jugadorService.obtenerPorEquipo(equipoId);
    }

}
