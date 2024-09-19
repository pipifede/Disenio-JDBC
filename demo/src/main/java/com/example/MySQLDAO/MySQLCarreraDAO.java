package com.example.MySQLDAO;
import java.util.List;

import com.example.Carrera;
import com.example.DAOFactory.CarreraDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class MySQLCarreraDAO implements CarreraDAO {
    private EntityManager entityManager;

    public MySQLCarreraDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Carrera> getCarreras(){
        String query = "SELECT * FROM Carrera";
        TypedQuery<Carrera> aux = entityManager.createQuery(query, Carrera.class);
        return aux.getResultList();
    }

    public List<Carrera> getCarrerasConInscriptos() {
        String jpql = "SELECT c " +
                      "FROM Inscripcion i JOIN i.carrera c " +
                      "GROUP BY c " +
                      "ORDER BY COUNT(i) DESC";
        TypedQuery<Carrera> query = entityManager.createQuery(jpql, Carrera.class);
        return query.getResultList();
    }
}
