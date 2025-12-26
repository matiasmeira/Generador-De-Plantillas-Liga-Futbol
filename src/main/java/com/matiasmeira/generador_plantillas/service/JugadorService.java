package com.matiasmeira.generador_plantillas.service;

import com.matiasmeira.generador_plantillas.model.Equipo;
import com.matiasmeira.generador_plantillas.model.Jugador;
import com.matiasmeira.generador_plantillas.repository.EquipoRepository;
import com.matiasmeira.generador_plantillas.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private EquipoRepository equipoRepository;

    public Jugador guardar(Jugador jugador, Long equipoId) {
        Equipo equipo = equipoRepository.findById(equipoId).orElse(null);
        jugador.setEquipo(equipo);
        return jugadorRepository.save(jugador);
    }

    public List<Jugador> obtenerPorEquipo(Long equipoId) {
        return jugadorRepository.findByEquipoId(equipoId);
    }
}
