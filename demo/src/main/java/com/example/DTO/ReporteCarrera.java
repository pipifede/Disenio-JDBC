package com.example.DTO;

import com.example.DAOFactory.InscripcionDAO;
import com.example.Entities.Estudiante;
import com.example.JPADAO.JPAInscripcionDAO;
import com.example.SearchStrategy.InscripcionSearchByCarrera;
import com.example.SearchStrategy.InscripcionSearchByGraduado;
import com.example.SearchStrategy.InscripcionSearchStrategy;

import java.util.List;

public class ReporteCarrera {
    public class ReporteAnual {
        private int anio;
        private List<Estudiante> inscriptos;
        private List<Estudiante> graduados;

        public ReporteAnual(int anio, List<Estudiante> inscriptos, List<Estudiante> graduados) {
            this.anio = anio;
            this.inscriptos = inscriptos;
            this.graduados = graduados;
        }

        public int getAnio() {
            return anio;
        }

        public void setAnio(int anio) {
            this.anio = anio;
        }

        public List<Estudiante> getInscriptos() {
            return inscriptos;
        }

        public void setInscriptos(List<Estudiante> inscriptos) {
            this.inscriptos = inscriptos;
        }

        public List<Estudiante> getGraduados() {
            return graduados;
        }

        public void setGraduados(List<Estudiante> graduados) {
            this.graduados = graduados;
        }
    }


    private int idCarrera;
    private List<ReporteAnual> inscripciones;

    public ReporteCarrera(int idCarrera){
        this.idCarrera = idCarrera;
        this.inscripciones = null;
    }

    public void addReporteAnual(int anio, List<Estudiante> inscriptos, List<Estudiante> graduados) {
        ReporteAnual reporteAnual = new ReporteAnual(anio, inscriptos, graduados);
        this.inscripciones.add(reporteAnual);
    }
    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

}
