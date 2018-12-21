package com.company.modelo.unidades;

import com.company.modelo.Jugador;

public class Espadachin extends UnidadAtacante {

    private final Integer VIDA_MAXIMA = 100;
    protected static Integer COSTO = 50;

    public Espadachin(Jugador jugador) {

        super(jugador);
        establecerEstadoInicial(VIDA_MAXIMA, COSTO);
        this.rangoAtaque = 1;
        this.danioAEdificio = 15;
        this.danioAUnidad = 25;
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
