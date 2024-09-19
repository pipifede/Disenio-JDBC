package com.example.SortStrategy;

public class EstudianteSortPorNombre implements EstudianteSortStrategy {

    @Override
    public String getOrden(String alias) {
        return "ORDER BY " + alias + ".nombre";
    }

    public static class EstudianteSortPorNombreAscendente implements EstudianteSortStrategy {
        @Override
        public String getOrden(String alias) {
            return "ORDER BY " + alias + ".nombre ASC";  // Orden ascendente
        }
    }

    public static class EstudianteSortPorNombreDescendente implements EstudianteSortStrategy {
        @Override
        public String getOrden(String alias) {
            return "ORDER BY " + alias + ".nombre DESC";  // Orden descendente
        }
    }
}