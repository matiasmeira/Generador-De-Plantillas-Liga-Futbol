package com.matiasmeira.generador_plantillas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matiasmeira.generador_plantillas.dto.LoginRequest;
import com.matiasmeira.generador_plantillas.dto.UsuarioDTO;
import com.matiasmeira.generador_plantillas.service.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO.Salida> crear(@RequestBody UsuarioDTO.Entrada usuario) {
        UsuarioDTO.Salida nuevoUsuario = usuarioService.guardar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO.Salida>> listar() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO.Salida> obtenerPorId(@PathVariable Long id) {
        UsuarioDTO.Salida usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO.Salida> login(@Valid @RequestBody LoginRequest loginRequest) {
        UsuarioDTO.Salida usuario = usuarioService.login(loginRequest.username(), loginRequest.password());
        return ResponseEntity.ok(usuario);
    }
}
