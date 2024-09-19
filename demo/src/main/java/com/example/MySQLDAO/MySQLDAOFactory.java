package com.example.MySQLDAO;

import com.example.DAOFactory.CarreraDAO;
import com.example.DAOFactory.DAOFactory;
import com.example.DAOFactory.EstudianteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MySQLDAOFactory extends DAOFactory {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    public void createConnection(String name){
        emf = Persistence.createEntityManagerFactory(name);
        em = emf.createEntityManager();
    };

//    @Override
//    public InscripcionDAO getInscripcionDAO() {
//        return new MySQLInscripcionDAO(em);
//    }

    @Override
    public CarreraDAO getCarreraDAO() {
        return new MySQLCarreraDAO(em);
    }

    @Override
    public EstudianteDAO getEstudianteDAO() {
        return new MySQLEstudianteDAO(em);
    }
}
