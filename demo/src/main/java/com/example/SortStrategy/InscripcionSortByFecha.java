package com.example.SortStrategy;

public class InscripcionSortByFecha implements InscripcionSortStrategy{
    @Override
    public String getOrden(String alias) {
        return "ORDER BY " + alias + ".fecha_inscripcion ASC";
    }
}
