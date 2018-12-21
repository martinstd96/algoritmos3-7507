package com.company.modelo.unidades;

import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.modelo.Jugador;
import com.company.modelo.Posicionable;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.estados.EstadoUnidad;
import com.company.modelo.unidades.estados.estadosAldeano.EstadoAldeano;
import com.company.modelo.unidades.estados.estadosAldeano.EstadoAldeanoRecolectandoOro;

import java.util.ArrayList;

public class Aldeano extends Unidad {

    private EstadoAldeano estadoActual;
    private Integer vida_Maxima = 50;

    public Aldeano(Jugador jugador) {
        super(jugador);
        estadoActual = new EstadoAldeanoRecolectandoOro(jugador,vida_Maxima);
    }
    @Override
    public Integer getCosto(){
        return estadoActual.getCosto();
    }

    @Override
    public Integer getVida(){
        return estadoActual.getVidaActual();
    }

    @Override
    public Integer getVidaMaxima() {
        return vida_Maxima;
    }

    @Override
    public void moverA(Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException,
            CasilleroLlenoException, MovimientoInvalidoException {
        posicion.moverA(posicionHorizontal, posicionVertical);
        try {
            estadoActual.abandonarTareaActual();
        } catch (Exception | EdificioEnConstruccionException ignored) {
        }
    }

    private boolean existentesEnRadio(Edificio e) {
        ArrayList<Posicionable> cercanos = this.posicion.buscarPosicionablesEnRadio(1);
        return cercanos.contains(e);
    }

    @Override
    public void recibirDanio(Integer montoDeDanio) throws Exception {
        try {
            estadoActual.recibirDanio(montoDeDanio);
        } catch (UnidadMuertaException | EdificioEnConstruccionException e) {
            posicion.quitarPosicionable();
            jugador.eliminarDePoblacion(this);

        }
    }

    public void construir(Edificio edificio, Integer x, Integer y)
            throws Exception, EdificioEnConstruccionException {
        if (posicion.esDistanciaValida(x, y)) {

            estadoActual = estadoActual.construir(edificio);

            edificio.construir(this, x, y);

        } else {
            throw new DistanciaInvalidaException("No puedo construir a esa distancia");
        }

    }

    public void reparar(Edificio edificio) throws EdificioLejanoException {
        if (existentesEnRadio(edificio)) {

            try {

                edificio.reparar(this);
                estadoActual = estadoActual.reparar(edificio);

            } catch (Exception | EdificioEnConstruccionException e) {
                //
            }

        } else {
            throw new EdificioLejanoException("Disculpe, esto esta muy lejos");
        }

    }

    public void liberar() throws Exception {
        estadoActual = new EstadoAldeanoRecolectandoOro(jugador,getVida());
        estadoActual.actualizar();
    }

    public void actualizar()  {
        try {
            estadoActual = estadoActual.actualizar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
