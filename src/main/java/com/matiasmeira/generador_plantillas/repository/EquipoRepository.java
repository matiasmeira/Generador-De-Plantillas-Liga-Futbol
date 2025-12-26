package com.matiasmeira.generador_plantillas.repository;

import com.matiasmeira.generador_plantillas.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}