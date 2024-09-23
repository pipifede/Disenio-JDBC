package com.example.MySQLDAO;

import java.util.List;
import java.util.Map;
import com.example.DAOFactory.EstudianteDAO;
import com.example.Estudiante;
import com.example.SearchStrategy.EstudianteSearchStrategy;
import com.example.SortStrategy.EstudianteSortStrategy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class MySQLEstudianteDAO implements EstudianteDAO {
    private EntityManager entityManager;

    public MySQLEstudianteDAO(EntityManager entityManager) {
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
        String jpql= "SELECT " + alias + " FROM Estudiante " + alias + " " + busqueda.buildSearchQuery(alias);

        TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);

        return query.getResultList();
    }

    public List<Estudiante> findEstudiantesBy2Filters(EstudianteSearchStrategy strategy1, EstudianteSearchStrategy strategy2){
        String alias = "e";
        String jpql = "SELECT e FROM Estudiante e " +
                "WHERE e.libretaUniversitaria IN (" + strategy1.buildSearchQuery("i") + ") " +
                "AND " + strategy2.buildSearchQuery(alias);

        TypedQuery<Estudiante> query = entityManager.createQuery(jpql, Estudiante.class);

        return query.getResultList();
    }
}




