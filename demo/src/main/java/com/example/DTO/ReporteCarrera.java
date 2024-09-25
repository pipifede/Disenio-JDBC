package com.example.DTO;

import com.example.DAOFactory.InscripcionDAO;
import com.example.Entities.Estudiante;
import com.example.JPADAO.JPAInscripcionDAO;
import com.example.SearchStrategy.InscripcionSearchByCarrera;
import com.example.SearchStrategy.InscripcionSearchByGraduado;
import com.example.SearchStrategy.InscripcionSearchStrategy;

import java.util.List;

public class ReporteCarrera {

    private int idCarrera;
    private List<Estudiante> inscriptos;
    private List<Estudiante> graduados;

    public ReporteCarrera(int idCarrera, List<Estudiante> inscriptos){
        this.idCarrera = idCarrera;
        this.inscriptos = inscriptos;
        this.graduados = null;
    }

    public void setInscriptos(List<Estudiante> inscriptos){
        this.inscriptos = inscriptos;
    }


    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
}
