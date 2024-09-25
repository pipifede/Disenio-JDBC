package com.example.SearchStrategy;

public class InscripcionSearchByAnio implements InscripcionSearchStrategy{
    private int anio;

    public InscripcionSearchByAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }

    public void setIdCarrera(int anio) {
        this.anio = anio;
    }

    @Override
    public String searchQuery() {
        return "i.anio="+ anio;
    }
}
