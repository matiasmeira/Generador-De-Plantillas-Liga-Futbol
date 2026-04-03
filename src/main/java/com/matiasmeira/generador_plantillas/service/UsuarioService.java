package com.matiasmeira.generador_plantillas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.generador_plantillas.dto.UsuarioDTO;
import com.matiasmeira.generador_plantillas.exception.BusinessRuleException;
import com.matiasmeira.generador_plantillas.exception.ResourceNotFoundException;
import com.matiasmeira.generador_plantillas.model.Usuario;
import com.matiasmeira.generador_plantillas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO.Salida guardar(UsuarioDTO.Entrada usuarioEntrada) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioEntrada.username());
        usuario.setPassword(usuarioEntrada.password());
        usuario.setRol(usuarioEntrada.rol());
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
        return mapToDto(usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id)));
    }

    public UsuarioDTO.Salida login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));         
        if (!usuario.getPassword().equals(password)) {
            throw new BusinessRuleException("Credenciales incorrectas");
        }
        return mapToDto(usuario);
    }

    public UsuarioDTO.Salida mapToDto(Usuario usuario) {
        if (usuario == null) return null;
        
        return new UsuarioDTO.Salida(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getRol()
        );
    }
}
