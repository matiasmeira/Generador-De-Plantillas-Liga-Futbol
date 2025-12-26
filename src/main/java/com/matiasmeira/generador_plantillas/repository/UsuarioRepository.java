package com.matiasmeira.generador_plantillas.repository;

import com.matiasmeira.generador_plantillas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aquí podrías agregar métodos personalizados después, como buscar por nombre de usuario
}