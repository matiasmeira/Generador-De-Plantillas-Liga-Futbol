package com.matiasmeira.generador_plantillas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.generador_plantillas.dto.JugadorDTO;
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

    public JugadorDTO.Salida guardar(JugadorDTO.Entrada jugadorDTO, Long equipoId) {
        Equipo equipo = equipoRepository.findById(equipoId).orElse(null);
        int cantidadJugadores = jugadorRepository.countByEquipoId(equipoId);
        if(cantidadJugadores >= 22) {
            throw new IllegalStateException("No se pueden agregar más de 22 jugadores a un equipo.");
        }
        Jugador jugador = new Jugador();
        jugador.setNombre(jugadorDTO.getNombre());
        jugador.setEquipo(equipo);
        jugador.setApellido(jugadorDTO.getApellido());
        jugador.setDni(jugadorDTO.getDni());
        jugador.setFechaNacimiento(jugadorDTO.getFechaNacimiento());
        return mapToDto(jugadorRepository.save(jugador));
    }

    public List<JugadorDTO.Salida> obtenerPorEquipo(Long equipoId) {
        List<Jugador> jugadores = jugadorRepository.findByEquipoId(equipoId);
        ArrayList<JugadorDTO.Salida> jugadoresDTO = new ArrayList<>();
        for (Jugador jugador : jugadores) {
            jugadoresDTO.add(mapToDto(jugador));
        }
        return jugadoresDTO;
    }
    
    public void eliminar(Long id) {
        if (!jugadorRepository.existsById(id)) {
            throw new IllegalStateException("El jugador con id " + id + " no existe.");
        }
        jugadorRepository.deleteById(id);
    }

    public JugadorDTO.Salida mapToDto(Jugador jugador) {
        JugadorDTO.Salida dto = new JugadorDTO.Salida();
        dto.setId(jugador.getId());
        dto.setNombre(jugador.getNombre());
        if (jugador.getEquipo() != null) {
            dto.setEquipoId(jugador.getEquipo().getId());
        }
        return dto;
    }
}
