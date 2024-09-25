package com.example.DAOFactory;

import com.example.MySQLDAO.MySQLDAOFactory;

public abstract class DAOFactory {
    public static final int MYSQL_JDBC = 1;

    public abstract CarreraDAO getCarreraDAO();

    public abstract EstudianteDAO getEstudianteDAO();

    public abstract InscripcionDAO getInscripcionDAO();

    public static DAOFactory getDAOFactory(int numFactory) {
        switch (numFactory) {
            case MYSQL_JDBC:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}
