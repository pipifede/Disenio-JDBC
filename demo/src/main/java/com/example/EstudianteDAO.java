package com.example;

import java.util.List;

import com.example.SearchStrategy.EstudianteSearchStrategy;
import com.example.SortStrategy.EstudianteSortStrategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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

    public List<Estudiante> getAllEstudiantes() {
        String jpql = "SELECT e FROM Estudiante e ";
        TypedQuery<Estudiante> aux = entityManager.createQuery(jpql, Estudiante.class);
        return aux.getResultList();
    }
    public List<Estudiante> getAllEstudiantes(EstudianteSortStrategy orden) {
        String alias = "e";
        String jpql = "SELECT " + alias + " FROM Estudiante " + alias + " " + orden.getOrden(alias);
        TypedQuery<Estudiante> aux = entityManager.createQuery(jpql, Estudiante.class);
        return aux.getResultList();
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
