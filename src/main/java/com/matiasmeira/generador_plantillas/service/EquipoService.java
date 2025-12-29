package com.matiasmeira.generador_plantillas.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matiasmeira.generador_plantillas.model.Equipo;
import com.matiasmeira.generador_plantillas.model.Usuario; // Importante para updates
import com.matiasmeira.generador_plantillas.repository.EquipoRepository;
import com.matiasmeira.generador_plantillas.repository.UsuarioRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Equipo guardar(Equipo equipo, Long usuarioId) {
        Usuario dueno = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        equipo.setUsuarioDueno(dueno);
        return equipoRepository.save(equipo);
    }

    public List<Equipo> obtenerTodos() {
        return equipoRepository.findAll();
    }

    /**
     * Actualiza un equipo validando permisos de ADMIN o Dueño.
     */
    @Transactional
    public Equipo actualizarConSeguridad(Long equipoId, Equipo datosNuevos, Long usuarioIdSesion, String rolSesion) {
        // 1. Buscar el equipo existente
        Equipo equipoExistente = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + equipoId));

        // 2. Validar permisos
        boolean esAdmin = "ADMIN".equalsIgnoreCase(rolSesion);
        
        // Comparamos el ID del dueño del equipo con el ID del usuario en sesión
        boolean esDueño = equipoExistente.getUsuarioDueno() != null && 
                          Objects.equals(equipoExistente.getUsuarioDueno().getId(), usuarioIdSesion);

        if (!esAdmin && !esDueño) {
            throw new RuntimeException("No tienes permiso para editar este equipo. Solo el dueño o un ADMIN pueden hacerlo.");
        }

        // 3. Actualizar solo los campos permitidos
        equipoExistente.setNombre(datosNuevos.getNombre());
        
        // Nota: No actualizamos el usuarioDueno para que el equipo no cambie de manos accidentalmente

        // 4. Guardar cambios
        return equipoRepository.save(equipoExistente);
    }
}