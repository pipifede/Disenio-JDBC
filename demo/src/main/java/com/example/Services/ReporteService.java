package com.example.Services;

import com.example.DTO.ReporteCarrera;
import com.example.Entities.Carrera;
import com.example.Entities.Inscripcion;
import com.example.SearchStrategy.InscripcionSearchByCarrera;
import com.example.SortStrategy.CarreraSortByNombre;
import com.example.SortStrategy.CarreraSortStrategy;
import com.example.SortStrategy.InscripcionSortByFecha;
import com.example.SortStrategy.InscripcionSortStrategy;

import java.util.ArrayList;
import java.util.List;

import com.example.DAOFactory.CarreraDAO;
import com.example.DAOFactory.InscripcionDAO;
import com.example.Entities.Carrera;
import com.example.Entities.Estudiante;
import com.example.Entities.Inscripcion;


public class ReporteService {
    private List<ReporteCarrera> reportes;

    public ReporteService(){
        this.reportes = new ArrayList<ReporteCarrera>();
    }
    public void generarReporte(CarreraDAO carreraDAO, InscripcionDAO inscripcionDAO) {
        CarreraSortStrategy strategySortCarrera = new CarreraSortByNombre();

        //sort de carreras por nombre
        List<Carrera> carreras = carreraDAO.getCarrerasSorteadas(strategySortCarrera);

        InscripcionSortStrategy strategySortInscripcion = new InscripcionSortByFecha();

        for (Carrera c : carreras) {
            List<Estudiante> inscriptos = new ArrayList<Estudiante>();
            List<Estudiante> graduados = new ArrayList<Estudiante>();

            InscripcionSearchByCarrera strategySearchCarrera = new InscripcionSearchByCarrera(c.getCarreraId());
            ReporteCarrera reporte = new ReporteCarrera(c.getCarreraId());

            //agarra las inscripciones de c ordenadas por fecha de inscripcion.
            List<Inscripcion> inscripciones = inscripcionDAO.getInscripcionByFilterOrdenadas(strategySearchCarrera, strategySortInscripcion);
            int indice = inscripciones.getFirst().getFecha_inscripcion().getYear();

            for (Inscripcion inscripcion : inscripciones) {
                if(indice == inscripcion.getFecha_inscripcion().getYear()){
                    if(inscripcion.isGraduado()){
                        graduados.add(inscripcion.getEstudiante());
                    } else {
                        inscriptos.add(inscripcion.getEstudiante());
                    }
                } else {
                    reporte.addReporteAnual(indice, inscriptos, graduados);
                    indice = inscripcion.getFecha_inscripcion().getYear();
                    inscriptos.clear();
                    graduados.clear();
                    if(inscripcion.isGraduado()){
                        graduados.add(inscripcion.getEstudiante());
                    }else{
                        inscriptos.add(inscripcion.getEstudiante());
                    }
                }
            }

            reporte.addReporteAnual(indice, inscriptos, graduados);
            this.reportes.add(reporte);
        }
    }

    public void printReporte() {
        for (ReporteCarrera reporteCarrera : reportes) {
            System.out.println(reporteCarrera.printReporte());
        }
    }
}
