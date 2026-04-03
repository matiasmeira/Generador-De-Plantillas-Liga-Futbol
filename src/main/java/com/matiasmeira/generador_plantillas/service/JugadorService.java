package com.matiasmeira.generador_plantillas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.generador_plantillas.dto.JugadorDTO;
import com.matiasmeira.generador_plantillas.exception.BusinessRuleException;
import com.matiasmeira.generador_plantillas.exception.ResourceNotFoundException;
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
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo no encontrado con ID: " + equipoId));
        int cantidadJugadores = jugadorRepository.countByEquipoId(equipoId);
        if(cantidadJugadores >= 22) {
            throw new BusinessRuleException("No se pueden agregar más de 22 jugadores al equipo.");
        }
        Jugador jugador = new Jugador();
        jugador.setNombre(jugadorDTO.nombre());
        jugador.setEquipo(equipo);
        jugador.setApellido(jugadorDTO.apellido());
        jugador.setDni(jugadorDTO.dni());
        jugador.setFechaNacimiento(jugadorDTO.fechaNacimiento());
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
            throw new ResourceNotFoundException("El jugador con ID " + id + " no existe.");
        }
        jugadorRepository.deleteById(id);
    }

    public JugadorDTO.Salida mapToDto(Jugador jugador) {
        Long equipoId = (jugador.getEquipo() != null) ? jugador.getEquipo().getId() : null;
        
        return new JugadorDTO.Salida(
            jugador.getId(),
            jugador.getNombre(),
            jugador.getApellido(),
            jugador.getFechaNacimiento(),
            jugador.getDni(),
            equipoId
        );
    }
}
