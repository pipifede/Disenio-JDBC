package com.example;

import java.util.Scanner;

import com.example.DAOFactory.CarreraDAO;
import com.example.DAOFactory.EstudianteDAO;
import com.example.DAOFactory.InscripcionDAO;
import com.example.MySQLDAO.MySQLDAOFactory;
import com.example.MySQLDAO.MySQLEstudianteDAO;
import com.example.SearchStrategy.EstudianteSearchByGenero;

import java.util.List;


//import org.hibernate.boot.archive.scan.spi.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.*;

public class Insert {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadDePersistencia");
    private static EntityManager em = emf.createEntityManager();
    private static MySQLDAOFactory MySQLDAOFactory = new MySQLDAOFactory();
    private static EstudianteDAO estudianteDAO;
    private static InscripcionDAO inscripcionDAO;
    private static CarreraDAO carreraDAO;
    public static void main(String[] args) {
        MySQLDAOFactory.createConnection("UnidadDePersistencia");
        estudianteDAO = MySQLDAOFactory.getEstudianteDAO();
        inscripcionDAO = MySQLDAOFactory.getInscripcionDAO();
        carreraDAO= MySQLDAOFactory.getCarreraDAO();
        
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
            System.out.println("0. Salir");
            EstudianteSearchByGenero queryGen = new EstudianteSearchByGenero();
            List<Estudiante> estudiantesGenero = estudianteDAO.findEstudiantes(queryGen);
            System.out.println(estudiantesGenero.get(0).toString());
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
                case 0:
                    exit = true;
                    System.out.println("Saliendo...");
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
            System.out.println("error");
        } catch (Exception e){
            System.out.println("Error al agregar estudiante:" + e);
        }
        
    }

    private static void matricularEstudiante(Scanner scanner) {
        System.out.println("Ingrese el número de libreta universitaria del estudiante:");
        long libretaUniversitaria = scanner.nextLong();
        System.out.println("Ingrese el ID de la carrera:");
        long carreraId = scanner.nextLong();

        Estudiante estudiante = em.find(Estudiante.class, libretaUniversitaria);
        Carrera carrera = em.find(Carrera.class, carreraId);

        if (estudiante != null && carrera != null) {
            Inscripcion inscripcion = new Inscripcion(estudiante, carrera);
            em.getTransaction().begin();
            em.persist(inscripcion);
            em.getTransaction().commit();
            System.out.println("Estudiante matriculado en la carrera.");
        } else {
            System.out.println("Estudiante o carrera no encontrados.");
        }
    }

    private static void recuperarTodosEstudiantes() {
        List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e ORDER BY e.nombre", Estudiante.class).getResultList();
        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre() + " " + e.getApellido());
        }
    }

    private static void recuperarEstudiantePorLibreta(Scanner scanner) {
        System.out.println("Ingrese el número de libreta universitaria del estudiante:");
        long libretaUniversitaria = scanner.nextLong();

        Estudiante estudiante = em.find(Estudiante.class, libretaUniversitaria);
        if (estudiante != null) {
            System.out.println("Estudiante encontrado: " + estudiante.getNombre() + " " + estudiante.getApellido());
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private static void recuperarEstudiantesPorGenero(Scanner scanner) {
        System.out.println("Ingrese el género (M/F):");
        String genero = scanner.nextLine();
        
        List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = :genero", Estudiante.class)
                                        .setParameter("genero", genero)
                                        .getResultList();
        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre() + " " + e.getApellido());
        }
    }

    private static void recuperarCarrerasConEstudiantes() {
        List<Object[]> result = em.createQuery(
                "SELECT c.nombreCarrera, COUNT(i) FROM Carrera c JOIN Inscripcion i ON c.carreraId = i.carrera.carreraId GROUP BY c.nombreCarrera ORDER BY COUNT(i) DESC")
                .getResultList();

        for (Object[] row : result) {
            System.out.println("Carrera: " + row[0] + ", Cantidad de inscriptos: " + row[1]);
        }
    }

    private static void recuperarEstudiantesPorCarreraYCiudad(Scanner scanner) {
        System.out.println("Ingrese el ID de la carrera:");
        long carreraId = scanner.nextLong();
        scanner.nextLine(); // Limpiar buffer
        System.out.println("Ingrese la ciudad de residencia:");
        String ciudad = scanner.nextLine();

        List<Estudiante> estudiantes = em.createQuery(
                "SELECT e FROM Inscripcion i JOIN i.estudiante e WHERE i.carrera.carreraId = :carreraId AND e.ciudadResidencia = :ciudad", Estudiante.class)
                .setParameter("carreraId", carreraId)
                .setParameter("ciudad", ciudad)
                .getResultList();

        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre() + " " + e.getApellido());
        }
    }
}