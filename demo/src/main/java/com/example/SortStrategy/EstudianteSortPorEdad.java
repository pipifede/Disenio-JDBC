package com.example.SortStrategy;

public class EstudianteSortPorEdad implements EstudianteSortStrategy {

    @Override
    public String getOrden(String alias) {
        return "ORDER BY " + alias + ".edad";  // Orden por defecto
    }

    public static class EstudianteSortPorEdadAscendente implements EstudianteSortStrategy {
        @Override
        public String getOrden(String alias) {
            return "ORDER BY " + alias + ".edad ASC";  // Orden ascendente
        }
    }

    public static class EstudianteSortPorEdadDescendente implements EstudianteSortStrategy {
        @Override
        public String getOrden(String alias) {
            return "ORDER BY " + alias + ".edad DESC";  // Orden descendente
        }
    }
    
}
