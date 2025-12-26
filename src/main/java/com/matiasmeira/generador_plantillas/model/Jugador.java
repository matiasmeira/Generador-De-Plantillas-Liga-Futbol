package com.matiasmeira.generador_plantillas.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "jugadores")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;

    @Column(unique = true)
    private String dni;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    @JsonIgnoreProperties("jugadores")
    private Equipo equipo;

    // --- GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public Equipo getEquipo() { return equipo; }
    public void setEquipo(Equipo equipo) { this.equipo = equipo; }
}