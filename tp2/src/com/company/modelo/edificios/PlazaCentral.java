package com.company.modelo.edificios;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.excepciones.MapaLlenoException;
import com.company.excepciones.UnidadErroneaException;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.estados.EstadoEdificioInactivo;
import com.company.modelo.edificios.estados.EstadoPorConstruir;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Unidad;

public class PlazaCentral extends Edificio {


    public PlazaCentral(Jugador jugador) {
        super(jugador);

        BLOQUES_DE_ALTO = 2;
        BLOQUES_DE_ANCHO = 2;
        MONTO_DE_REPARACION = 25;
        COSTO = 100;
        VIDA_MAXIMA = 450;

        this.estado = new EstadoPorConstruir(VIDA_MAXIMA,VIDA_MAXIMA, MONTO_DE_REPARACION);
    }

    public void surgir(Integer posicionHorizontal, Integer posicionVertical) throws CasilleroNoExistenteException, CasilleroLlenoException {
        this.ubicar(posicionHorizontal, posicionVertical);
        estado = new EstadoEdificioInactivo(VIDA_MAXIMA,VIDA_MAXIMA,MONTO_DE_REPARACION);
        jugador.agregarAEdificios(this);
    }

    @Override
    public void crear(Unidad unidad) throws EdificioNoDisponibleException, UnidadErroneaException, CasilleroLlenoException, CasilleroNoExistenteException, MapaLlenoException {

        if( !(estado instanceof EstadoEdificioInactivo) )
            throw new EdificioNoDisponibleException("El edificio no esta disponible");


        if( !(unidad instanceof Aldeano) )
            throw new UnidadErroneaException("Imposible crear ese tipo de unidad");

        posiciones.get(0).colocarEnCasilleroLibreMasCercano(unidad);
        jugador.cobrar(unidad.getCosto());
        jugador.agregarAPoblacion(unidad);
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
