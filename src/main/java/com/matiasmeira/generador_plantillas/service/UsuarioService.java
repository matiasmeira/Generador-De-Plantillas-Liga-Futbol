package com.matiasmeira.generador_plantillas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matiasmeira.generador_plantillas.model.Usuario;
import com.matiasmeira.generador_plantillas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null); 
    }
    public Usuario login(String username, String password) {
    return usuarioRepository.findAll().stream()
            .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
            .findFirst()
            .orElse(null); 
    }
}
