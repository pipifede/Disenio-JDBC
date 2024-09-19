package com.example;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CarreraDAO {
    private EntityManager entityManager;

    public CarreraDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<Carrera> getCarreras(){
        String query = "SELECT * FROM Carrera";
        TypedQuery<Carrera> aux = entityManager.createQuery(query, Carrera.class);
        return aux.getResultList();
    }

    public List<Carrera> getCarrerasConEstudiantes(){
        String query = "SELECT c.nombreCarrera, COUNT(i) FROM Carrera c JOIN Inscripcion i ON c.carreraId = i.carrera.carreraId GROUP BY c.nombreCarrera ORDER BY COUNT(i) DESC";
        TypedQuery<Carrera> aux = entityManager.createQuery(query, Carrera.class);
        return aux.getResultList();
    }
}
