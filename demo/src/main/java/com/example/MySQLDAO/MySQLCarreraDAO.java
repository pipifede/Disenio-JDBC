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
        String query = "SELECT c FROM Carrera c";
        TypedQuery<Carrera> aux = entityManager.createQuery(query, Carrera.class);
        return aux.getResultList();
    }

    public List<Carrera> getCarrerasConInscriptos() { //recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
        String jpql = "SELECT c " +
                      "FROM Inscripcion i JOIN i.carrera c " +
                      "GROUP BY c " +
                      "ORDER BY COUNT(i) DESC"; //ordena por cantidad de inscriptos
        TypedQuery<Carrera> query = entityManager.createQuery(jpql, Carrera.class);
        return query.getResultList();
    }
}
