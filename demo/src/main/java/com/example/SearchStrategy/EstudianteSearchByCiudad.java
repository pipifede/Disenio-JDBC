package com.example.SearchStrategy;

import java.util.HashMap;
import java.util.Map;

public class EstudianteSearchByCiudad implements EstudianteSearchStrategy{
    private String ciudadResidencia;

    public EstudianteSearchByCiudad(String ciudadResidencia){
        this.ciudadResidencia = ciudadResidencia;
    }

    @Override
    public String buildSearchQuery(String alias) {
        return alias +".ciudadResidencia = '"+ ciudadResidencia + "'";
    }

    public String getCiudadDeResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadDeResidencia(String ciudadDeResidencia) {
        this.ciudadResidencia = ciudadDeResidencia;
    }
}
