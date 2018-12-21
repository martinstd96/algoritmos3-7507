package com.company.vista.gui.eventos.ejecutadores;

import com.company.controlador.Controlador;
import com.company.excepciones.ArmaDesmontadaException;
import com.company.excepciones.ArmaMontadaException;
import com.company.excepciones.CastilloDestruidoExcecption;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.vista.gui.eventos.selecciones.GestorDeSelecciones;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Montador implements Ejecutador {
    @Override
    public void ejecutar(Integer xOrigen, Integer yOrigen, Integer xDestino, Integer yDestino) throws Exception, ArmaDesmontadaException, ArmaMontadaException, EdificioEnConstruccionException, EdificioNoDisponibleException, CastilloDestruidoExcecption {
        Mapa mapa = Mapa.getMapa();
        Controlador controlador = Controlador.getControlador();
        ArmaAsedio armaAsedio =(ArmaAsedio)mapa.conseguirOcupante(xOrigen,yOrigen);
        controlador.montar(armaAsedio);
        controlador.pasarTurno();

        String archivoDeMusica = "trebuchetcreak.mp3";
        Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
        MediaPlayer reproductorMontar = new MediaPlayer(archivo);
        reproductorMontar.setAutoPlay(true);
    }
}
