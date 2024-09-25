package com.example.DAOFactory;

import java.util.List;

import com.example.Entities.Carrera;
import com.example.SortStrategy.CarreraSortStrategy;
import com.example.SortStrategy.EstudianteSortStrategy;

public interface CarreraDAO {
    public List<Carrera> getCarreras();
    public List<Carrera> getCarrerasSorteadas(CarreraSortStrategy orden);
    public List<Carrera> getCarrerasConInscriptos();
}
