package com.example.DAOFactory;

import java.util.List;

import com.example.Entities.Carrera;

public interface CarreraDAO {
    public List<Carrera> getCarreras();
    public List<Carrera> getCarrerasConInscriptos();
}
