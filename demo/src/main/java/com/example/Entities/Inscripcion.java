package com.example.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    ///CURSANDO O NO ///////

    public Inscripcion(){};
    public Inscripcion(Estudiante estudiante, Carrera carrera) {
        this.estudiante = estudiante;
        this.carrera = carrera;
    }
}
