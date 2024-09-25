package com.example.JPADAO;

import com.example.DAOFactory.InscripcionDAO;
import com.example.Entities.Estudiante;
import com.example.Entities.Inscripcion;

import com.example.SearchStrategy.InscripcionSearchStrategy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    public List<Estudiante> getEstudiantesByFilter(InscripcionSearchStrategy strategy){
        String jpql = "SELECT i.estudiante FROM Inscripcion i WHERE" + strategy.searchQuery();
        TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);

        return query.getResultList();
    }

    public List<Estudiante> getEstudiantesBy2Filter(InscripcionSearchStrategy strategy1, InscripcionSearchStrategy strategy2){
        String jpql = "SELECT i.estudiante FROM Inscripcion i " +
                "WHERE "+ strategy1.searchQuery() +
                " AND " + strategy2.searchQuery();

        TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);

        return query.getResultList();
    }

    public List<Estudiante> getEstudiantesBy3Filter(InscripcionSearchStrategy strategy1, InscripcionSearchStrategy strategy2, InscripcionSearchStrategy strategy3){
        String jpql = "SELECT i.estudiante FROM Inscripcion i " +
                "WHERE "+ strategy1.searchQuery() +
                " AND " + strategy2.searchQuery() +
                " AND " + strategy3.searchQuery();

        TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);

        return query.getResultList();
    }

    
}
