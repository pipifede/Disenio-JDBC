package com.example.SearchStrategy;

public class EstudianteSearchByGenero implements EstudianteSearchStrategy{
    private String genero; //enum a genero

    public EstudianteSearchByGenero(){
        this.genero = "masculino";
    }

    @Override
    public String buildSearchQuery(String alias) {
        return alias +".genero =" + genero;
    }

    public void setGeneroMasculino(){this.genero = "masculino";};//enum a genero
    public void setGeneroFemenino(){this.genero = "femenino";};//enum a genero
}
