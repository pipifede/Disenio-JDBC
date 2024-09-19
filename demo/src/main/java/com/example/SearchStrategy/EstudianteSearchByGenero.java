package com.example.SearchStrategy;

import java.util.List;

import com.example.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EstudianteSearchByGenero implements EstudianteSearchStrategy{
    private String genero; //enum a genero

    public EstudianteSearchByGenero(){
        this.genero = "masculino";
    }

    @Override
    public List<Estudiante> search(EntityManager entityManager) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero =" + this.genero;
        TypedQuery<Estudiante> aux = entityManager.createQuery(jpql, Estudiante.class);
        return aux.getResultList();
    }
    
    public void setGeneroMasculino(){this.genero = "masculino";};//enum a genero
    public void setGeneroFemenino(){this.genero = "femenino";};//enum a genero
}
