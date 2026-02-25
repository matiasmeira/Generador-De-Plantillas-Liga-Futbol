package com.matiasmeira.generador_plantillas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matiasmeira.generador_plantillas.model.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    List<Jugador> findByEquipoId(Long equipoId);
    
    int countByEquipoId(Long equipoId);

}
