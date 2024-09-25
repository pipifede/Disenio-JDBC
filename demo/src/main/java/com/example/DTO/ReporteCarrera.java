package com.example.DTO;

import com.example.Entities.Estudiante;

import java.util.List;

public class ReporteCarrera {

    private int idCarrera;
    private List<Estudiante> inscriptos;
    private List<Estudiante> graduados;

    public ReporteCarrera(int: id){
        
    }
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public List<Estudiante> getInscriptos() {
        return inscriptos;
    }

    public void setInscriptos(List<Estudiante> inscriptos) {
        this.inscriptos = inscriptos;
    }

    public List<Estudiante> getGraduados() {
        return graduados;
    }

    public void setGraduados(List<Estudiante> graduados) {
        this.graduados = graduados;
    }
}
