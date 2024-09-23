package com.example.SearchStrategy;

public class EstudianteSearchByCarrera implements EstudianteSearchStrategy{
    int idCarrera;

    public EstudianteSearchByCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    @Override
    public String buildSearchQuery(String alias) {
        return "SELECT i.estudiante.libretaUniversitaria FROM Inscripcion i WHERE i.carrera.carreraId = " + idCarrera;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
}
