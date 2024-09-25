package com.example.DAOFactory;

import com.example.Entities.Estudiante;
import com.example.Entities.Inscripcion;
import com.example.SearchStrategy.InscripcionSearchStrategy;

import java.util.List;

public interface InscripcionDAO {
    public abstract void addInscripcion(Inscripcion inscripcion);
    public abstract List<Estudiante> getEstudiantesByFilter(InscripcionSearchStrategy strategy);
    public abstract List<Estudiante> getEstudiantesBy2Filter(InscripcionSearchStrategy strategy1, InscripcionSearchStrategy strategy2);

}
