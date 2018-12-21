package com.company.modelo.unidades;

import com.company.excepciones.ArmaDesmontadaException;
import com.company.excepciones.EnemigoInvalidoException;
import com.company.excepciones.PosicionableEsAliadoException;
import com.company.excepciones.UnidadMuertaException;
import com.company.modelo.Ataque;
import com.company.modelo.Jugador;
import com.company.modelo.Posicionable;
import com.company.modelo.edificios.Edificio;
public abstract class UnidadAtacante extends Unidad {

    protected Integer rangoAtaque;
    protected Integer danioAEdificio;
    protected Integer danioAUnidad;

    public UnidadAtacante(Jugador jugador) {
        super(jugador);
    }

    public void atacarA(Unidad enemigo) throws EnemigoInvalidoException, ArmaDesmontadaException {

        this.atacar(enemigo, this.danioAUnidad);
    }

    public void atacarA(Edificio enemigo) throws EnemigoInvalidoException, ArmaDesmontadaException {

        this.atacar(enemigo, this.danioAEdificio);
    }

    protected void atacar(Posicionable unEnemigo, Integer unDanio) throws EnemigoInvalidoException {

        Ataque ataque = new Ataque(rangoAtaque, jugador, posicion);
        ataque.atacar(unEnemigo, unDanio);
    }

}
