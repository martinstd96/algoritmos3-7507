package com.company.vista.gui.eventos.ejecutadores;

import com.company.controlador.Controlador;
import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.Cuartel;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ConstructorDeCuartel implements Ejecutador {
    @Override
    public void ejecutar(Integer xOrigen, Integer yOrigen, Integer xDestino, Integer yDestino) throws Exception, ArmaDesmontadaException, ArmaMontadaException, EdificioEnConstruccionException, CastilloDestruidoExcecption {
        Controlador controlador = Controlador.getControlador();
        Mapa mapa = Mapa.getMapa();
        Aldeano aldeano = (Aldeano) mapa.conseguirOcupante(xOrigen,yOrigen);
        Jugador jugador = aldeano.getJugador();
        Cuartel cuartel = new Cuartel(jugador);
        controlador.construirCuartel(aldeano,cuartel,xDestino,yDestino);
        controlador.pasarTurno();

        String archivoDeMusica = "build2.mp3";
        Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
        MediaPlayer reproductorMontar = new MediaPlayer(archivo);
        reproductorMontar.setAutoPlay(true);
    }
}
