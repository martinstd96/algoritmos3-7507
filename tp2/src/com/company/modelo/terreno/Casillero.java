package com.company.modelo.terreno;

import com.company.excepciones.CasilleroLlenoException;
import com.company.modelo.Posicionable;

public class Casillero {

    private Posicionable habitante;

    public Casillero(){
        habitante = null;
    }

    public Boolean estaOcupado() {

        return ( habitante != null );
    }

    public void agregarPosicionable(Posicionable entidadNueva) throws CasilleroLlenoException {

        if(!this.estaOcupado()){
            habitante = entidadNueva;

        } else {
            throw new CasilleroLlenoException("Se intentó llenar un casillero ocupado");
        }

    }

    public Posicionable obtenerPosicionable(){

        return habitante;
    }

    public void quitarPosicionable(){
        habitante = null;
    }

}
