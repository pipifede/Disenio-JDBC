package com.example.SearchStrategy;

import java.util.List;

import com.example.Estudiante;

import jakarta.persistence.EntityManager;

public class EstudianteSearchByEdad implements EstudianteSearchStrategy{

    @Override
    public List<Estudiante> search(EntityManager entityManager) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }
    
}
