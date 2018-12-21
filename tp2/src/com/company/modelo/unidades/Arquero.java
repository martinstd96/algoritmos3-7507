package com.company.modelo.unidades;

import com.company.modelo.Jugador;

public class Arquero extends UnidadAtacante {

    private final Integer VIDA_MAXIMA = 75;
    protected static Integer COSTO = 75;

    public Arquero(Jugador jugador) {

        super(jugador);
        establecerEstadoInicial(VIDA_MAXIMA, COSTO);
        this.rangoAtaque = 3;
        this.danioAEdificio = 10;
        this.danioAUnidad = 15;
    }

    @Override
    public Integer getVidaMaxima() {
        return VIDA_MAXIMA;
    }

    @Override
    public void actualizar() {
        //no se actualiza
    }
}
