package com.company.modelo.unidades;

import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.modelo.Jugador;
import com.company.modelo.Posicion;
import com.company.modelo.Posicionable;
import com.company.modelo.unidades.estados.EstadoUnidad;
import com.company.modelo.unidades.estados.EstadoUnidadInactivo;

public abstract class Unidad extends Posicionable {

    protected Posicion posicion;
    protected EstadoUnidad estado;

    public Unidad(Jugador jugador) {
        this.jugador = jugador;
        this.posicion = null;

    }

    //devuelve el estado de la unidad inicial
    public void establecerEstadoInicial(Integer vida_maxima,Integer costo){
        estado = new EstadoUnidadInactivo(vida_maxima,vida_maxima,costo);
    }

    public Integer getCosto(){
        return estado.getCosto();
    }

    public Integer getVida(){

        return estado.getVidaActual();
    }

    public void establecerCoordenadasDeNacimiento(Integer posicionHorizontal, Integer posicionVertical) {
        posicion = new Posicion(posicionHorizontal, posicionVertical);
    }

    public void moverA(Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException,
            ArmaMontadaException, MovimientoInvalidoException {

        posicion.moverA(posicionHorizontal, posicionVertical);
    }

    public void recibirDanio(Integer montoDeDanio) throws Exception, EdificioEnConstruccionException, UnidadMuertaException {
        try {
            estado.recibirDanio(montoDeDanio);
        } catch (UnidadMuertaException e) {
            this.eliminarDePosicion();
            jugador.eliminarDePoblacion(this);
        }
    }

    private void eliminarDePosicion() {
        if (posicion != null) {
            posicion.quitarPosicionable();
        }
    }

    @Override
    public void ubicar(Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException {
        posicion.posicionar(this);
    }

}
