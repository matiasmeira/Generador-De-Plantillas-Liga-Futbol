package com.matiasmeira.generador_plantillas.service;

import com.matiasmeira.generador_plantillas.model.Equipo;
import com.matiasmeira.generador_plantillas.model.Usuario;
import com.matiasmeira.generador_plantillas.repository.EquipoRepository;
import com.matiasmeira.generador_plantillas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Equipo guardar(Equipo equipo, Long usuarioId) {
        Usuario dueno = usuarioRepository.findById(usuarioId).orElse(null);
        equipo.setUsuarioDueno(dueno);
        return equipoRepository.save(equipo);
    }
    
    public List<Equipo> obtenerTodos() {
        return equipoRepository.findAll();
    }
}
