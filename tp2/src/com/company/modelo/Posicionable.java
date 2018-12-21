package com.company.modelo;

import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;

public abstract class Posicionable {

    protected Jugador jugador;

    // Queda abstracto porque los edificios y las unidades se ubican de formas distintas
    public abstract void ubicar(Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException;

    public abstract void recibirDanio(Integer montoDeDanio)
            throws Exception, EdificioEnConstruccionException, EdificioDestruidoExcepcion, UnidadMuertaException;

    public abstract Integer getVida() throws Exception;

    public abstract Integer getVidaMaxima();

    public Jugador getJugador(){
        return jugador;
    }

    public abstract void actualizar() throws CastilloDestruidoExcecption;
}

