package com.example.DAOFactory;

import com.example.Carrera;

import java.util.List;

public interface CarreraDAO {
    public List<Carrera> getCarreras();
    public List<Carrera> getCarrerasConInscriptos();
}
