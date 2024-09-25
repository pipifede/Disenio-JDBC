package com.example.SearchStrategy;

public class InscripcionSearchByGraduado implements InscripcionSearchStrategy{
    private boolean graduado;

    public InscripcionSearchByGraduado(boolean graduado){
        this.graduado = graduado;
    }

    public boolean isGraduado() {
        return graduado;
    }

    public void setGraduado(boolean graduado) {
        this.graduado = graduado;
    }

    @Override
    public String searchQuery() {
        return "i.graduado ="+ graduado;
    }
}
