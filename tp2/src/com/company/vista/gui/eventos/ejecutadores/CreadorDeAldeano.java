package com.company.vista.gui.eventos.ejecutadores;

import com.company.controlador.Controlador;
import com.company.excepciones.ArmaDesmontadaException;
import com.company.excepciones.ArmaMontadaException;
import com.company.excepciones.CastilloDestruidoExcecption;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.PlazaCentral;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.terreno.Mapa;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class CreadorDeAldeano implements Ejecutador {
    @Override
    public void ejecutar(Integer xOrigen, Integer yOrigen, Integer xDestino, Integer yDestino) throws Exception, ArmaDesmontadaException, ArmaMontadaException, EdificioEnConstruccionException, EdificioNoDisponibleException, CastilloDestruidoExcecption {
        Controlador controlador = Controlador.getControlador();
        Mapa mapa = Mapa.getMapa();
        PlazaCentral plazaCentral = (PlazaCentral) mapa.conseguirOcupante(xOrigen,yOrigen);
        Jugador jugador = plazaCentral.getJugador();
        Aldeano aldeano = new Aldeano(jugador);
        controlador.crearAldeano(plazaCentral, aldeano);
        controlador.pasarTurno();

        String archivoDeMusica = "militarycreation.mp3";
        Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
        MediaPlayer reproductorMontar = new MediaPlayer(archivo);
        reproductorMontar.setAutoPlay(true);
    }
}
