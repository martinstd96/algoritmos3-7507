package com.company.vista.gui.eventos.ejecutadores;

import com.company.controlador.Controlador;
import com.company.excepciones.ArmaDesmontadaException;
import com.company.excepciones.ArmaMontadaException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.CastilloDestruidoExcecption;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.ArmaAsedio;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Desmontador implements Ejecutador {
    @Override
    public void ejecutar(Integer xOrigen, Integer yOrigen, Integer xDestino, Integer yDestino) throws CasilleroNoExistenteException, ArmaDesmontadaException, ArmaMontadaException, CastilloDestruidoExcecption {
        Mapa mapa = Mapa.getMapa();
        Controlador controlador = Controlador.getControlador();
        ArmaAsedio armaAsedio =(ArmaAsedio)mapa.conseguirOcupante(xOrigen,yOrigen);
        controlador.desmontar(armaAsedio);
        controlador.pasarTurno();

        String archivoDeMusica = "trebuchetmove.mp3";
        Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
        MediaPlayer reproductorMontar = new MediaPlayer(archivo);
        reproductorMontar.setAutoPlay(true);
    }
}
