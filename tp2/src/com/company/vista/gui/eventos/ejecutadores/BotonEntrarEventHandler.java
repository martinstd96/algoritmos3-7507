package com.company.vista.gui.eventos.ejecutadores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class BotonEntrarEventHandler implements EventHandler<ActionEvent> {

    private Stage stage;
    private Scene proximaEscena;
    private MediaPlayer reproductorInicial;

    public BotonEntrarEventHandler(Stage stage, Scene proximaEscena, MediaPlayer reproductorInicial) {
        this.stage = stage;
        this.proximaEscena = proximaEscena;
         this.reproductorInicial = reproductorInicial;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        stage.setScene(proximaEscena);

        reproductorInicial.stop();
        String archivoDeMusica = "OriginalSoundtrack.mp3";
        Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
        MediaPlayer reproductorDeFondo = new MediaPlayer(archivo);
        reproductorDeFondo.setAutoPlay(true);
        reproductorDeFondo.setVolume(0.2);
        reproductorDeFondo.setCycleCount(MediaPlayer.INDEFINITE);

        stage.setMaximized(true);
    }
}