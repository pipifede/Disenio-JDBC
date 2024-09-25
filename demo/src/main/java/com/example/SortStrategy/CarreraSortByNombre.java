package com.example.SortStrategy;

public class CarreraSortByNombre implements CarreraSortStrategy{
    @Override
    public String getOrden() {
        return "ORDER BY c.nombreCarrera ASC";
    }
}
