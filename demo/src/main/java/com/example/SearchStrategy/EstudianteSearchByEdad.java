package com.example.SearchStrategy;

import java.util.List;

import com.example.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EstudianteSearchByEdad implements EstudianteSearchStrategy{
    private int edad;
    private String comparador;

    public EstudianteSearchByEdad(){
        this.edad = 10;
        this.comparador = "=";
    }
    public EstudianteSearchByEdad(int edad){
        this.edad = edad;
        this.comparador = "=";
    }

    @Override
    public List<Estudiante> search(EntityManager entityManager) {
        String jpql = "SELECT e FROM Estudiante e WHERE e.genero ="+ this.comparador + this.edad;
        TypedQuery<Estudiante> aux = entityManager.createQuery(jpql, Estudiante.class);
        return aux.getResultList();
    }
    
    public void setEdad(int edad){this.edad = edad;}
    public void setComparadorMayor() {this.comparador = ">";};
    public void setComparadorMenor() {this.comparador = "<";};
    public void setComparadorIgual() {this.comparador = "=";};  
}
