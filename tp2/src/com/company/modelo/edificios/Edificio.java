package com.company.modelo.edificios;

import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioEnReparacionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.excepciones.Edificio.ErrorDeConstruccionException;
import com.company.modelo.Jugador;
import com.company.modelo.Posicion;
import com.company.modelo.Posicionable;
import com.company.modelo.edificios.estados.EstadoEdificio;
import com.company.modelo.edificios.estados.EstadoPorConstruir;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Unidad;

import java.util.ArrayList;

public abstract class Edificio extends Posicionable {

    protected Integer VIDA_MAXIMA;
    protected Integer COSTO;
    protected Integer MONTO_DE_REPARACION;
    protected Integer BLOQUES_DE_ANCHO;
    protected Integer BLOQUES_DE_ALTO;
    final Integer SIN_VIDA = 0;

    protected ArrayList<Posicion> posiciones;
    protected EstadoEdificio estado;

    public Edificio(Jugador jugador){

        posiciones = new ArrayList<>();

        this.jugador = jugador;

        this.estado = new EstadoPorConstruir(VIDA_MAXIMA,VIDA_MAXIMA,COSTO);
    }

	public void recibirDanio(Integer unDanio)
            throws EdificioEnConstruccionException {
        try {
            estado = estado.recibirDanio(unDanio);
        }catch(EdificioDestruidoExcepcion e){
            jugador.eliminarDeConstrucciones(this);
            this.eliminar();
        }
	}

    public void construir(Aldeano quienLoConstruye, Integer posicionHorizontal,
                          Integer posicionVertical) throws Exception, ErrorDeConstruccionException {
        jugador.cobrar(this.COSTO);
        this.ubicar(posicionHorizontal, posicionVertical);

        estado = estado.construir(quienLoConstruye);
        jugador.agregarAEdificios(this);
    }

    public void avanzarConstruccion() throws Exception {

        estado = estado.ejecutarAccion();
    }

    public void suspender() throws Exception, EdificioEnConstruccionException {
        estado = estado.suspender();
    }

    @Override
    public void ubicar(Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException {

        for( int i = posicionHorizontal; i < ( posicionHorizontal + BLOQUES_DE_ANCHO ); i++)
            for (int j = posicionVertical; j < (posicionVertical + BLOQUES_DE_ALTO); j++) {
                Posicion posicion = new Posicion(i, j);
                posicion.posicionar(this);
                posiciones.add(posicion);
            }
    }

    public Integer getVida() throws Exception {

        return this.estado.getVidaActual();
    }

    public void crear(Unidad unidad) throws CasilleroNoExistenteException, EdificioEnReparacionException, CasilleroLlenoException, EdificioEnConstruccionException, MapaLlenoException, EdificioNoDisponibleException, UnidadErroneaException {
        this.estado = estado.crear(unidad, posiciones.get(1));
        jugador.cobrar(unidad.getCosto());
        jugador.agregarAPoblacion(unidad);
    }
    
    public void reparar(Aldeano reparador) throws Exception, EdificioEnConstruccionException {
        this.estado = this.estado.reparar(reparador, MONTO_DE_REPARACION);
    }

    public void eliminar() {
        int longitud = posiciones.size();

        for (int i = longitud - 1; i >= 0; i--) {
            Posicion posicion = posiciones.remove(i);
            posicion.quitarPosicionable();
        }
    }

    public void avanzarReparacion() throws Exception {
        this.estado = estado.ejecutarAccion();
    }
}
