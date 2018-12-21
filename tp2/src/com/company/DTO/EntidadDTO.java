package com.company.DTO;

import com.company.vista.gui.GeneradorDeBotones;

import java.util.ArrayList;

public class EntidadDTO {

    private Integer jugador;
    private String nombre;
    private ArrayList<Accion> acciones;
    private GeneradorDeBotones generadorDeBotones = GeneradorDeBotones.getGenerador();


    public EntidadDTO(Integer jugador, String nombre, ArrayList<Accion> acciones){
        this.jugador = jugador;
        this.nombre = nombre;
        this.acciones = acciones;
    }

    public String getNombre(){
        return nombre;
    }

    public ArrayList<Accion> getAcciones(){
        return acciones;
    }

    public Integer getJugador(){
        return jugador;
    }

    public void generarBotones(){
        generadorDeBotones.generarBotones(getAcciones());
    }
}
