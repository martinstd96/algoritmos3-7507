package com.company.vista.gui.eventos.ejecutadores;

import com.company.controlador.Controlador;
import com.company.excepciones.ArmaDesmontadaException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.CastilloDestruidoExcecption;
import com.company.excepciones.EnemigoInvalidoException;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.modelo.unidades.Arquero;
import com.company.modelo.unidades.Espadachin;
import com.company.modelo.unidades.UnidadAtacante;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Atacador implements Ejecutador {
    @Override
    public void ejecutar(Integer xOrigen, Integer yOrigen, Integer xDestino, Integer yDestino) throws CasilleroNoExistenteException, ArmaDesmontadaException, EnemigoInvalidoException, CastilloDestruidoExcecption {
        Controlador controlador = Controlador.getControlador();
        Mapa mapa = Mapa.getMapa();
        UnidadAtacante unidad = (UnidadAtacante) mapa.conseguirOcupante(xOrigen,yOrigen);
        controlador.atacar(unidad,xDestino,yDestino);
        controlador.pasarTurno();

        if(unidad instanceof Espadachin){
            String archivoDeMusica = "fight5.mp3";
            Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
            MediaPlayer reproductorMontar = new MediaPlayer(archivo);
            reproductorMontar.setAutoPlay(true);
        }

        else if(unidad instanceof Arquero){
            String archivoDeMusica = "arrow3.mp3";
            Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
            MediaPlayer reproductorMontar = new MediaPlayer(archivo);
            reproductorMontar.setAutoPlay(true);
        }

        else if(unidad instanceof ArmaAsedio){
            String archivoDeMusica = "trebuchetfire2.mp3";
            Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
            MediaPlayer reproductorMontar = new MediaPlayer(archivo);
            reproductorMontar.setAutoPlay(true);
        }

        else{
            String archivoDeMusica = "fireball6.mp3";
            Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
            MediaPlayer reproductorMontar = new MediaPlayer(archivo);
            reproductorMontar.setAutoPlay(true);
        }

    }
}
