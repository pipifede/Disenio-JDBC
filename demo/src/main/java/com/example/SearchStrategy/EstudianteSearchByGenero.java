package com.example.SearchStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EstudianteSearchByGenero implements EstudianteSearchStrategy{
    private String genero; //enum a genero

    public EstudianteSearchByGenero(){
        this.genero = "masculino";
    }

    @Override
    public String buildSearchQuery(String alias) {
        return "WHERE "+ alias +".genero =" + genero;
    }

    public void setGeneroMasculino(){this.genero = "masculino";};//enum a genero
    public void setGeneroFemenino(){this.genero = "femenino";};//enum a genero
}
