package com.example.SearchStrategy;

import java.util.List;

import com.example.Estudiante;

import jakarta.persistence.EntityManager;

public interface EstudianteSearchStrategy {
    List<Estudiante> search(EntityManager entityManager);
}
