package com.example.MySQLDAO;

import com.example.Inscripcion;
import com.example.DAOFactory.InscripcionDAO;

import jakarta.persistence.EntityManager;

public class MySQLInscripcionDAO implements InscripcionDAO{
    private EntityManager entityManager;

    public MySQLInscripcionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public void addInscripcion(Inscripcion inscripcion) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(inscripcion);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al agregar estudiante:" + e);
        }
    }
    
}
