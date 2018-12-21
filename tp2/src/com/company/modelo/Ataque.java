package com.company.modelo;

import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.EdificioDestruidoExcepcion;
import com.company.excepciones.EnemigoInvalidoException;

import java.util.ArrayList;

public class Ataque {

    private Integer rangoAtaque;
    private Jugador jugador;
    private Posicion posicion;

    public Ataque(Integer rangoDeAtaque, Jugador jugador, Posicion posicion){
        this.rangoAtaque = rangoDeAtaque;
        this.jugador = jugador;
        this.posicion = posicion;
    }

    public void atacar(Posicionable unEnemigo, Integer unDanio)
            throws EnemigoInvalidoException {

        ArrayList enemigos = buscarEnemigos(this.rangoAtaque);

        if( !enemigos.contains(unEnemigo) )
            throw new EnemigoInvalidoException("No se pudo atacar al enemigo");

        try{
            unEnemigo.recibirDanio(unDanio);
            return;

        } catch (Exception | EdificioDestruidoExcepcion | EdificioEnConstruccionException ignored) {
        }
    }

    public void atacarATodos(Integer unDanio) {
        ArrayList<Posicionable> enemigosProvisorios = buscarEnemigos(this.rangoAtaque);
        ArrayList<Posicionable> enemigosDefinitivos = new ArrayList<>();

        for (Posicionable unPosicionable : enemigosProvisorios) {

            if( !( enemigosDefinitivos.contains(unPosicionable) ) ) enemigosDefinitivos.add(unPosicionable);

        }

        for(Posicionable unEnemigo : enemigosDefinitivos) {

            try{
                unEnemigo.recibirDanio(unDanio);
            } catch (Exception | EdificioDestruidoExcepcion | EdificioEnConstruccionException ignored) {
            }

        }

    }

    private void eliminarPosicionablesAmigos(ArrayList<Posicionable> atacables){
        ArrayList edificios = jugador.getEdificios();
        ArrayList poblacion = jugador.getPoblacion();

        for(int i = atacables.size() - 1; i >= 0; i--){
            Posicionable unPosicionable = atacables.get(i);

            if( edificios.contains(unPosicionable) || poblacion.contains(unPosicionable) ){
                atacables.remove(unPosicionable);
            }

        }

    }

    private ArrayList<Posicionable> buscarAtacables(Integer unRadio){
        return posicion.buscarPosicionablesEnRadio(unRadio);
    }

    private ArrayList<Posicionable> buscarEnemigos(Integer unRadio){
        ArrayList<Posicionable> atacables = this.buscarAtacables(unRadio);
        this.eliminarPosicionablesAmigos(atacables);
        return atacables;
    }

}
