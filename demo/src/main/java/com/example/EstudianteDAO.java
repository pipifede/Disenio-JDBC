package com.example;

import java.util.List;

import com.example.SearchStrategy.EstudianteSearchStrategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EstudianteDAO {
    private EntityManager entityManager;

    public EstudianteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Dar de alta un estudiante
    public void addEstudiante(Estudiante estudiante) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(estudiante);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al agregar estudiante:" + e);
        }
    }

    public Estudiante getEstudianteByLibreta(long libretaUniversitaria) {
        return entityManager.find(Estudiante.class, libretaUniversitaria);
    }

    public Estudiante getEstudianteByNumeroDocumento(long numeroDeDocumento) {
        return entityManager.find(Estudiante.class, numeroDeDocumento);
    }

    public List<Estudiante> findEstudiantes(EstudianteSearchStrategy strategy) {
        return strategy.search(entityManager);
    }
}
