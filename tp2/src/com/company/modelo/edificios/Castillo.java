package com.company.modelo.edificios;

import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.modelo.Ataque;
import com.company.modelo.Jugador;
import com.company.modelo.Posicionable;
import com.company.modelo.edificios.estados.EstadoEdificioInactivo;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.modelo.unidades.Unidad;
import javafx.geometry.Pos;

import java.util.ArrayList;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

public class Castillo extends Edificio {


    final Integer MONTO_DE_REPARACION = 15;
    final Integer COSTO = 0;

    final Integer VIDA_MAXIMA = 1000;

    private Integer rangoAtaque;
    private Integer danioAPosicionable;

    public Castillo(Jugador jugador) {
        super(jugador);
        BLOQUES_DE_ALTO = 4;
        BLOQUES_DE_ANCHO = 4;
        this.rangoAtaque = 5; // porque es a partir del centro del castillo
        this.danioAPosicionable = 20;
        this.estado = new EstadoEdificioInactivo(VIDA_MAXIMA, VIDA_MAXIMA, MONTO_DE_REPARACION);
    }

    @Override
    public void crear(Unidad unidad) throws CasilleroNoExistenteException,
            CasilleroLlenoException, UnidadErroneaException, MapaLlenoException {


        if (!(unidad instanceof ArmaAsedio)) {
            throw new UnidadErroneaException("Imposible crear ese tipo de unidad");
        }

        posiciones.get(1).colocarEnCasilleroLibreMasCercano(unidad);
        jugador.cobrar(this.COSTO);
        jugador.agregarAPoblacion(unidad);
    }

    public void surgir(Integer posicionHorizontal, Integer posicionVertical) throws CasilleroNoExistenteException, CasilleroLlenoException {
        this.ubicar(posicionHorizontal, posicionVertical);
        jugador.agregarAEdificios(this);
    }

    public void atacar(Integer unDanio) throws EnemigoInvalidoException {
        Ataque ataque = new Ataque(rangoAtaque, jugador, posiciones.get(9)); //la posicion 9 es donde esta el centro del castillo
        ataque.atacarATodos(unDanio);
    }

    @Override
    public Integer getVidaMaxima() {
        return VIDA_MAXIMA;
    }

    @Override
    public void actualizar() throws CastilloDestruidoExcecption {
        try {
            if( estado.getVidaActual() <= 0 ) throw new CastilloDestruidoExcecption();
            try {
                this.atacar(20); //a todos les saca la misma vida
            } catch (EnemigoInvalidoException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recibirDanio(Integer unDanio)
            throws EdificioEnConstruccionException {
        try {
            estado = estado.recibirDanio(unDanio);
        }catch(EdificioDestruidoExcepcion e){
            return;
        }
    }


}
