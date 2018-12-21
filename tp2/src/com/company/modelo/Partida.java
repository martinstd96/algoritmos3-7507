package com.company.modelo;

import com.company.controlador.Controlador;
import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.CastilloDestruidoExcecption;
import com.company.modelo.terreno.Mapa;

import java.util.ArrayList;

public class Partida {

    private Turno turno;
    private static Integer NUMERO_DE_JUGADORES = 2;
    private ArrayList<Jugador> jugadores = new ArrayList<>();

    public Partida() throws CasilleroNoExistenteException, CasilleroLlenoException {
        turno = new Turno();

        for (Integer i = 0; i < NUMERO_DE_JUGADORES; i++){
            Jugador jugador = new Jugador();
            turno.sumarJugador(jugador);
            jugadores.add(jugador);
        }

        for (Jugador jugador : jugadores) { jugador.crearEntidadesIniciales(); }

        Controlador controlador = Controlador.getControlador();
        controlador.setPartida(this);
    }

    public void pasarTurno() throws CastilloDestruidoExcecption {
        turno.pasar();
    }

    public Jugador obtenerJugadorCorriente(){
        return turno.obtenerJugadorCorriente();
    }

}
