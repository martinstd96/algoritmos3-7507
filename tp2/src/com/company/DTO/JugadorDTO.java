package com.company.DTO;

import com.company.modelo.Jugador;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.Unidad;
import com.company.vista.gui.GeneradorDeBotones;

import java.util.ArrayList;

public class JugadorDTO {
    private Integer numeroDeJugador;
    private Integer oro;
    private Integer poblacionCorriente;
    private Integer poblacionMaxima;
    private ArrayList<Unidad> poblacion;
    private ArrayList<Edificio> edificios;

    public JugadorDTO(Jugador jugador){
        this.oro = jugador.getOro();
        this.numeroDeJugador = jugador.getNumeroDeJugador();
        this.poblacionCorriente = jugador.getPoblacion().size();
        this.poblacionMaxima = jugador.getLimitePoblacional();
        this.poblacion = jugador.getPoblacion();
        this.edificios = jugador.getEdificios();
    }

    public Integer getNumeroDeJugador(){
        return numeroDeJugador;
    }

    public Integer getOro(){
        return oro;
    }

    public Integer getPoblacionCorriente(){
        return poblacionCorriente;
    }

    public Integer getPoblacionMaxima() {
        return poblacionMaxima;
    }

    public ArrayList<Unidad> getPoblacion(){
        return poblacion;
    }

    public ArrayList<Edificio> getEdificios(){
        return edificios;
    }

    public void mostrarInformacion(){
        GeneradorDeBotones generadorDeBotones = GeneradorDeBotones.getGenerador();
        generadorDeBotones.mostrarInformacion(this);
    }
}
