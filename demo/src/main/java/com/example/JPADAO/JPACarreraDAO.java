package com.example.JPADAO;
import java.util.List;

import com.example.DAOFactory.CarreraDAO;
import com.example.Entities.Carrera;

import com.example.Entities.Estudiante;
import com.example.SortStrategy.CarreraSortStrategy;
import com.example.SortStrategy.EstudianteSortStrategy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import javax.swing.text.Caret;

public class JPACarreraDAO implements CarreraDAO {
    private EntityManager entityManager;

    public JPACarreraDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Carrera> getCarreras(){
        String query = "SELECT c FROM Carrera c";
        TypedQuery<Carrera> aux = entityManager.createQuery(query, Carrera.class);
        return aux.getResultList();
    }

    public List<Carrera> getCarrerasSorteadas(CarreraSortStrategy orden) {
        String jpql = "SELECT c FROM Carrera c " + orden.getOrden();
        TypedQuery<Carrera> aux = entityManager.createQuery(jpql, Carrera.class);
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
