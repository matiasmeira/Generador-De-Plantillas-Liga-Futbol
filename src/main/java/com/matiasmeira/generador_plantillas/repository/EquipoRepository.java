package com.matiasmeira.generador_plantillas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.matiasmeira.generador_plantillas.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    @Query("SELECT e FROM Equipo e")
    List<Equipo> findAllLite();
}