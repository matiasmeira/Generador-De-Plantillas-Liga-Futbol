package com.matiasmeira.generador_plantillas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matiasmeira.generador_plantillas.dto.AuthResponse;
import com.matiasmeira.generador_plantillas.dto.UsuarioDTO;
import com.matiasmeira.generador_plantillas.exception.BusinessRuleException;
import com.matiasmeira.generador_plantillas.exception.ResourceNotFoundException;
import com.matiasmeira.generador_plantillas.model.Usuario;
import com.matiasmeira.generador_plantillas.repository.UsuarioRepository;
import com.matiasmeira.generador_plantillas.security.JwtService;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UsuarioDTO.Salida guardar(UsuarioDTO.Entrada usuarioEntrada) {
        if (usuarioRepository.findByUsername(usuarioEntrada.username()).isPresent()) {
            logger.warn("Attempt to register with existing username: {}", usuarioEntrada.username());
            throw new BusinessRuleException("El username ya está en uso");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioEntrada.username());
        usuario.setPassword(passwordEncoder.encode(usuarioEntrada.password()));
        usuario.setRol(defaultRole(usuarioEntrada.rol()));

        Usuario savedUsuario = usuarioRepository.save(usuario);
        logger.info("User registered successfully: {}", savedUsuario.getUsername());
        return mapToDto(savedUsuario);
    }

    public AuthResponse login(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (AuthenticationException ex) {
            logger.warn("Failed login attempt for username: {}", username);
            throw new BusinessRuleException("Credenciales incorrectas");
        }

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        String token = jwtService.generateToken(usuario);
        logger.info("User logged in successfully: {}", username);
        return new AuthResponse(token, mapToDto(usuario));
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
        Objects.requireNonNull(id, "El ID no puede ser null");
        return mapToDto(usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id)));
    }

    public UsuarioDTO.Salida mapToDto(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioDTO.Salida(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getRol()
        );
    }

    private String defaultRole(String rol) {
        if (rol == null || rol.isBlank()) {
            return "ROLE_USER";
        }
        return rol.startsWith("ROLE_") ? rol : "ROLE_" + rol;
    }
}
