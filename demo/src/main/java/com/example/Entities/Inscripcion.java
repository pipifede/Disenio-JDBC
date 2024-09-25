package com.example.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Inscripcion {
    @Id
    @ManyToOne
    @JoinColumn(name = "estudianteId", referencedColumnName = "libretaUniversitaria")
    private Estudiante estudiante;

    @Id
    @ManyToOne
    @JoinColumn(name = "carreraId", referencedColumnName = "carreraId")
    private Carrera carrera;

    @Column(nullable = false)
    private boolean graduado;

    @Column(nullable = false)
    private LocalDateTime fecha_inscripcion;
    ///CURSANDO O NO ///////

    public Inscripcion(){};
    public Inscripcion(Estudiante estudiante, Carrera carrera, boolean graduado, LocalDateTime fechaInscripcion) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.graduado = graduado;
        this.fecha_inscripcion = fechaInscripcion;

    }
    public Inscripcion(Estudiante estudiante, Carrera carrera, boolean graduado) {
        this.estudiante = estudiante;
        this.carrera = carrera;
        this.graduado = graduado;
        this.fecha_inscripcion = LocalDateTime.now();

    }
}
