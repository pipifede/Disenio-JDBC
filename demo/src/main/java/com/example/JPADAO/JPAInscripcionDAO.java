package com.example.JPADAO;

import com.example.DAOFactory.InscripcionDAO;
import com.example.Entities.Inscripcion;

import jakarta.persistence.EntityManager;

public class JPAInscripcionDAO implements InscripcionDAO{
    private EntityManager entityManager;

    public JPAInscripcionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addInscripcion(Inscripcion inscripcion) { //Matricular un estudiante en una carrera
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(inscripcion);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al agregar estudiante:" + e);
        }
    }
    
}
