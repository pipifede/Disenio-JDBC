package com.example;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int carreraId;
    @Column
    private String nombreCarrera;
    @Column
    private LocalDateTime fechaCreacion;
    public Carrera(String nombreCarrera, LocalDateTime fechaCreacion) {
        this.nombreCarrera = nombreCarrera;
        this.fechaCreacion = fechaCreacion;
    }
    public String getNombreCarrera() {
        return nombreCarrera;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
