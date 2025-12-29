package com.matiasmeira.generador_plantillas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.generador_plantillas.model.Equipo;
import com.matiasmeira.generador_plantillas.model.Jugador;
import com.matiasmeira.generador_plantillas.repository.EquipoRepository;
import com.matiasmeira.generador_plantillas.repository.JugadorRepository;

@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private EquipoRepository equipoRepository;

    public Jugador guardar(Jugador jugador, Long equipoId) {
        Equipo equipo = equipoRepository.findById(equipoId).orElse(null);
        int cantidadJugadores = jugadorRepository.countByEquipoId(equipoId);
        if(cantidadJugadores >= 22) {
            throw new IllegalStateException("No se pueden agregar más de 22 jugadores a un equipo.");
        }
        jugador.setEquipo(equipo);
        return jugadorRepository.save(jugador);
    }

    public List<Jugador> obtenerPorEquipo(Long equipoId) {
        return jugadorRepository.findByEquipoId(equipoId);
    }
    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }
}
