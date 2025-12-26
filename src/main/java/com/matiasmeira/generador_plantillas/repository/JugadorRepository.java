package com.matiasmeira.generador_plantillas.repository;

import com.matiasmeira.generador_plantillas.model.Jugador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    List<Jugador> findByEquipoId(Long equipoId);
}
