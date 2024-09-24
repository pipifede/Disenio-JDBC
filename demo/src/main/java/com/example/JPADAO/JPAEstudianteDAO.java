package com.example.JPADAO;

import java.util.List;
import com.example.DAOFactory.EstudianteDAO;
import com.example.Entities.Estudiante;
import com.example.SearchStrategy.EstudianteSearchStrategy;
import com.example.SortStrategy.EstudianteSortStrategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class JPAEstudianteDAO implements EstudianteDAO {
    private EntityManager entityManager;

    public JPAEstudianteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Dar de alta un estudiante
    public void addEstudiante(Estudiante estudiante) {
        try {
            estudiante.setCiudadResidencia(estudiante.getCiudadResidencia().toLowerCase());
            entityManager.getTransaction().begin();
            entityManager.persist(estudiante);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error al agregar estudiante:" + e);
        }
    }

    public List<Estudiante> getAllEstudiantes() {
        String jpql = "SELECT e FROM Estudiante e ORDER BY e.nombre ASC";
        TypedQuery<Estudiante> aux = entityManager.createQuery(jpql, Estudiante.class);
        return aux.getResultList();
    }
    public List<Estudiante> getAllEstudiantes(EstudianteSortStrategy orden) {
        String alias = "e";
        String jpql = "SELECT " + alias + " FROM Estudiante " + alias + " " + orden.getOrden(alias);
        TypedQuery<Estudiante> aux = entityManager.createQuery(jpql, Estudiante.class);
        return aux.getResultList();
    }

    public Estudiante getEstudianteByLibreta(long libretaUniversitaria) { //recuperar un estudiante, en base a su número de libreta universitaria.
        return entityManager.find(Estudiante.class, libretaUniversitaria);
    }

    public Estudiante getEstudianteByNumeroDocumento(long numeroDeDocumento) {
        return entityManager.find(Estudiante.class, numeroDeDocumento);
    }

    public List<Estudiante> findEstudiantes(EstudianteSearchStrategy busqueda) {
        String alias = "e";
        String jpql= "SELECT " + alias + " FROM Estudiante " + alias + " WHERE " + busqueda.buildSearchQuery(alias);

        TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);

        return query.getResultList();
    }

    public List<Estudiante> findEstudiantes(EstudianteSearchStrategy strategy1, EstudianteSearchStrategy strategy2){
        String alias = "e";
        String jpql = "SELECT "+ alias +" FROM Estudiante "+ alias +" " +
                "WHERE "+ strategy1.buildSearchQuery(alias) +
                " AND " + strategy2.buildSearchQuery(alias);

        TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);

        return query.getResultList();
    }
}




