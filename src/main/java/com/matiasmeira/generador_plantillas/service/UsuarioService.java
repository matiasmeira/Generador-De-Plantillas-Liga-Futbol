package com.matiasmeira.generador_plantillas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.generador_plantillas.dto.UsuarioDTO;
import com.matiasmeira.generador_plantillas.model.Usuario;
import com.matiasmeira.generador_plantillas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO.Salida guardar(UsuarioDTO.Entrada usuarioEntrada) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioEntrada.getUsername());
        usuario.setPassword(usuarioEntrada.getPassword());
        usuario.setRol(usuarioEntrada.getRol());
        return mapToDto(usuarioRepository.save(usuario));
    }

    public List<UsuarioDTO.Salida> obtenerTodos() {
        List<UsuarioDTO.Salida> usuariosDTO = new ArrayList<>();
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {
            usuariosDTO.add(mapToDto(usuario));
        }
        return usuariosDTO;
    }
    
    public UsuarioDTO.Salida obtenerPorId(Long id) {
        return mapToDto(usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }

    public UsuarioDTO.Salida login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (usuario.getPassword().equals(password)) {
            return mapToDto(usuario);
        }
        return null;
    }

    public UsuarioDTO.Salida mapToDto(Usuario usuario) {
        UsuarioDTO.Salida usuarioSalida = new UsuarioDTO.Salida();
        usuarioSalida.setId(usuario.getId());
        usuarioSalida.setUsername(usuario.getUsername());
        usuarioSalida.setRol(usuario.getRol());
        return usuarioSalida;
    }
}
