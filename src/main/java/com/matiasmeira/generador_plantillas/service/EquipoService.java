package com.matiasmeira.generador_plantillas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matiasmeira.generador_plantillas.dto.EquipoDTO;
import com.matiasmeira.generador_plantillas.dto.JugadorDTO;
import com.matiasmeira.generador_plantillas.model.Equipo;
import com.matiasmeira.generador_plantillas.model.Usuario; 
import com.matiasmeira.generador_plantillas.repository.EquipoRepository;
import com.matiasmeira.generador_plantillas.repository.UsuarioRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private UsuarioService usuarioService;

    public EquipoDTO.Salida guardar(EquipoDTO.Entrada equipoDTO, Long usuarioId) {
        Usuario dueno = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Equipo equipo = new Equipo();
        equipo.setNombre(equipoDTO.nombre());
        equipo.setUsuarioDueno(dueno);
        return mapToDTO(equipoRepository.save(equipo));
    }

    public List<EquipoDTO.Salida> obtenerTodos() {
        List<Equipo> equipos = equipoRepository.findAll();
        List<EquipoDTO.Salida> equiposDto = new ArrayList<>();
        for (Equipo equipo : equipos) {
            equiposDto.add(mapToDTO(equipo));
        }
        return equiposDto;
    }

    public List<EquipoDTO.Lite> obtenerTodosLite() {
        return equipoRepository.findAllLite().stream()
                .map(equipo -> new EquipoDTO.Lite(
                        equipo.getId(),
                        equipo.getNombre(),
                        usuarioService.mapToDto(equipo.getUsuarioDueno())
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public EquipoDTO.Salida actualizarConSeguridad(Long equipoId, Equipo datosNuevos, Long usuarioIdSesion, String rolSesion) {

        Equipo equipoExistente = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + equipoId));

        boolean esAdmin = "ADMIN".equalsIgnoreCase(rolSesion);  
        boolean esDueño = equipoExistente.getUsuarioDueno() != null && 
                          Objects.equals(equipoExistente.getUsuarioDueno().getId(), usuarioIdSesion);

        if (!esAdmin && !esDueño) {
            throw new RuntimeException("No tienes permiso para editar este equipo. Solo el dueño o un ADMIN pueden hacerlo.");
        }

        equipoExistente.setNombre(datosNuevos.getNombre());
        
        return mapToDTO(equipoRepository.save(equipoExistente));
    }

    public EquipoDTO.Salida mapToDTO(Equipo equipo) {
        if (equipo == null) return null;
        
        // Manejamos la lista con Streams. Si es null, devolvemos una lista vacía para evitar NullPointerException.
        List<JugadorDTO.Salida> jugadoresDto = (equipo.getJugadores() != null) 
            ? equipo.getJugadores().stream().map(jugadorService::mapToDto).collect(Collectors.toList())
            : List.of();

        return new EquipoDTO.Salida(
            equipo.getId(),
            equipo.getNombre(),
            usuarioService.mapToDto(equipo.getUsuarioDueno()),
            jugadoresDto
        );
    }
}