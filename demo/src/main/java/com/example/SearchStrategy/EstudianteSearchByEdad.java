package com.example.SearchStrategy;

import com.example.Estudiante;
import java.util.Map;
import java.util.HashMap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EstudianteSearchByEdad implements EstudianteSearchStrategy{
    private int edad;

    public EstudianteSearchByEdad(int edad){
        this.edad = edad;
    }

    @Override
    public String buildSearchQuery(String alias) {
        return "WHERE "+ alias +".edad ="+edad;
    }

    public void setEdad(int edad){this.edad = edad;}
}
