package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.DAOFactory.CarreraDAO;
import com.example.DAOFactory.DAOFactory;
import com.example.DAOFactory.EstudianteDAO;
import com.example.DAOFactory.InscripcionDAO;
import com.example.DTO.ReporteCarrera;
import com.example.Entities.Carrera;
import com.example.Entities.Estudiante;
import com.example.Entities.Inscripcion;
import com.example.SearchStrategy.*;

import java.util.List;

import com.example.Services.ReporteService;
import com.example.SortStrategy.CarreraSortByNombre;
import com.example.SortStrategy.CarreraSortStrategy;
import com.example.SortStrategy.InscripcionSortByFecha;
import com.example.SortStrategy.InscripcionSortStrategy;
import jakarta.persistence.*;

public class App {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadDePersistencia");
    private static EntityManager em = emf.createEntityManager();
    private static DAOFactory jpaDAOFactory = DAOFactory.getDAOFactory(1);
    private static EstudianteDAO estudianteDAO;
    private static InscripcionDAO inscripcionDAO;
    private static CarreraDAO carreraDAO;
    public static void main(String[] args) {
        jpaDAOFactory.createConnection("UnidadDePersistencia");
        estudianteDAO = jpaDAOFactory.getEstudianteDAO();
        inscripcionDAO = jpaDAOFactory.getInscripcionDAO();
        carreraDAO= jpaDAOFactory.getCarreraDAO();
        
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Dar de alta un estudiante");
            System.out.println("2. Matricular un estudiante en una carrera");
            System.out.println("3. Recuperar todos los estudiantes (ordenar por nombre)");
            System.out.println("4. Recuperar estudiante por número de libreta universitaria");
            System.out.println("5. Recuperar todos los estudiantes por género");
            System.out.println("6. Recuperar carreras con estudiantes inscriptos, ordenado por cantidad de inscriptos");
            System.out.println("7. Recuperar estudiantes de una carrera, filtrado por ciudad de residencia");
            System.out.println("8. Agregar una nueva carrera");
            System.out.println("9. Generar reporte");
            System.out.println("0. Salir");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            

            switch (option) {
                case 1:
                    darAltaEstudiante(scanner);
                    break;
                case 2:
                    matricularEstudiante(scanner);
                    break;
                case 3:
                    recuperarTodosEstudiantes();
                    break;
                case 4:
                    recuperarEstudiantePorLibreta(scanner);
                    break;
                case 5:
                    recuperarEstudiantesPorGenero(scanner);
                    break;
                case 6:
                    recuperarCarrerasConEstudiantes();
                    break;
                case 7:
                    recuperarEstudiantesPorCarreraYCiudad(scanner);
                    break;
                case 8:
                    darAltaCarrera(scanner);
                    break;
                case 9:
                    generarReportes();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }

        em.close();
        emf.close();
        scanner.close();
    }


    private static void darAltaEstudiante(Scanner scanner) {
        System.out.println("Ingrese el nombre del estudiante:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del estudiante:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese la edad del estudiante:");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.println("Ingrese la ciudad de residencia:");
        String ciudad = scanner.nextLine();
        System.out.println("Ingrese el número de documento:");
        long documento = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer
        System.out.println("Ingrese el genero:");
        String genero = scanner.nextLine();

        Estudiante estudiante = new Estudiante(documento, nombre, apellido, edad, ciudad, genero);
        
        try {
            estudianteDAO.addEstudiante(estudiante);
            System.out.println("Estudiante dado de alta exitosamente.");
        } catch (EntityExistsException e) {
            System.out.println("Error");
        } catch (Exception e){
            System.out.println("Error al agregar estudiante:" + e);
        }
        
    }

    private static void matricularEstudiante(Scanner scanner) {
        System.out.println("Ingresa el numero de libreta universitaria del estudiante:");
        long libretaUniversitaria = scanner.nextLong();
        System.out.println("Ingrese el ID de la carrera:");
        long carreraId = scanner.nextLong();

        Estudiante estudiante = em.find(Estudiante.class, libretaUniversitaria);
        Carrera carrera = em.find(Carrera.class, carreraId);

        if (estudiante != null && carrera != null) {
            Inscripcion inscripcion = new Inscripcion(estudiante, carrera, false);
            inscripcionDAO.addInscripcion(inscripcion);

            System.out.println("Estudiante matriculado en la carrera.");
        } else {
            System.out.println("Estudiante o carrera no encontrados.");
        }
    }

    private static void recuperarTodosEstudiantes() {
        List<Estudiante> estudiantes = estudianteDAO.getAllEstudiantes();

        for (Estudiante e : estudiantes) {
            System.out.println(e);
        }
    }

    private static void recuperarEstudiantePorLibreta(Scanner scanner) {
        System.out.println("Ingrese el número de libreta universitaria del estudiante:");
        long libretaUniversitaria = scanner.nextLong();

        Estudiante estudiante = estudianteDAO.getEstudianteByLibreta(libretaUniversitaria);
        if (estudiante != null) {
            System.out.println("Estudiante encontrado por su libreta: " + estudiante);
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void recuperarEstudiantesPorGenero(Scanner scanner) {
        EstudianteSearchByGenero criterio = new EstudianteSearchByGenero();
        
        System.out.println("Ingrese el género (M/F):");
        String genero = scanner.nextLine();
        if (genero.equalsIgnoreCase("f")){criterio.setGeneroFemenino();};

        List<Estudiante> estudiantes = estudianteDAO.findEstudiantes(criterio);
        for (Estudiante e: estudiantes){System.out.println(e);};
    }

    private static void recuperarCarrerasConEstudiantes() {
        List<Carrera> carreras = carreraDAO.getCarrerasConInscriptos();
        
        for (Carrera c: carreras){System.out.println(c);}
    }

    private static void recuperarEstudiantesPorCarreraYCiudad(Scanner scanner) {
        System.out.println("Ingrese el ID de la carrera:");
        int carreraId = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.println("Ingrese la ciudad de residencia:");
        String ciudad = scanner.nextLine();

        EstudianteSearchStrategy strategy1 = new EstudianteSearchByCiudad(ciudad);
        EstudianteSearchStrategy strategy2 = new EstudianteSearchByCarrera(carreraId);

        List<Estudiante> estudiantes = estudianteDAO.findEstudiantes(strategy2, strategy1);

        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre() + " " + e.getApellido());
        }
    }

    private static void darAltaCarrera(Scanner scanner){
        System.out.println("Ingrese el nombre de la carrera:");
        String nombre = scanner.nextLine();
        Carrera carrera = new Carrera(nombre,LocalDateTime.now());

        try {
            carreraDAO.addCarrera(carrera);
            System.out.println("Carrera dada de alta exitosamente.");
        } catch (EntityExistsException e) {
            System.out.println("Error");
        } catch (Exception e){
            System.out.println("Error al agregar carrera:" + e);
        }
    };

    private static void generarReportes() {
        ReporteService reporte = new ReporteService();
        reporte.generarReporte(carreraDAO, inscripcionDAO);
        reporte.printReporte();
    }
}