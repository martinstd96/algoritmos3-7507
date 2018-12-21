package com.company.vista.gui;

import com.company.vista.gui.eventos.OpcionSalirEventHandler;
import com.company.vista.gui.eventos.ejecutadores.BotonEntrarEventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

import static javafx.scene.input.KeyCode.ENTER;

public class PantallaInicial extends VBox {

    Stage stage;

    public PantallaInicial(Stage stage, Scene proximaEscena) {

        super();

        this.stage = stage;

        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(20);
        this.setPadding(new Insets(25));
        this.setPrefSize(800,615);

        Label etiqueta = new Label();
        etiqueta.setFont(Font.font("Arial Black", FontWeight.BOLD, 50));

        etiqueta.setText("ALGO EMPIRES");
        etiqueta.setTextFill(Color.rgb(95, 2, 31)); // anterior 150, 100, 20


        Button botonEntrar = new Button();
        botonEntrar.setText("Nueva partida");
        botonEntrar.setMinSize(200, 60);
        botonEntrar.setTextFill(Color.rgb(200, 150, 50));
        botonEntrar.setFont(Font.font("Lucida Console", FontWeight.BOLD,40));
        botonEntrar.setStyle("-fx-base: #394867; -fx-border-color: rgb(249,219,189)");

        Button botonSalir = new Button();
        botonSalir.setMinSize(200, 60);
        botonSalir.setText("Salir");
        botonSalir.setTextFill(Color.rgb(200, 150, 70));
        botonSalir.setFont(Font.font("Lucida Console", 40));
        botonSalir.setStyle("-fx-base: #394867; -fx-border-color: rgb(150,180,189)");

        OpcionSalirEventHandler botonSalirHandler = new OpcionSalirEventHandler();
        botonSalir.setOnAction(botonSalirHandler);

        String imagen = "AlgoEmpires.jpg";
        Image imagenInicio = new Image(new File(imagen).toURI().toString());
        BackgroundSize backgroundSize = new BackgroundSize(50, 50, true, true, true, false);
        BackgroundImage fondoImPantallaInicial = new BackgroundImage(imagenInicio, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background fondoPantallaInicial = new Background(fondoImPantallaInicial);
        this.setBackground(fondoPantallaInicial);
        /*try{
            Image inicio = new Image(getClass().getResourceAsStream("AlgoEmpires.jpg"));
            BackgroundSize backgroundSize = new BackgroundSize(50, 50, true, true, true, false);
            BackgroundImage fondoImPantallaInicial = new BackgroundImage(inicio, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background fondoPantallaInicial = new Background(fondoImPantallaInicial);
            this.setBackground(fondoPantallaInicial);
        } catch (Exception e){
            this.setBackground(new Background(new BackgroundFill(Color.BROWN, new CornerRadii(2), null)));
        }*/

        String archivoDeMusica = "ThemeSong.mp3";
        Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
        MediaPlayer reproductorInicial = new MediaPlayer(archivo);
        reproductorInicial.setAutoPlay(true);
        reproductorInicial.setCycleCount(MediaPlayer.INDEFINITE);

        BotonEntrarEventHandler botonEntrarHandler = new BotonEntrarEventHandler(stage, proximaEscena, reproductorInicial);
        botonEntrar.setOnAction(botonEntrarHandler);


        this.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == ENTER){
                botonEntrar.fire();
            }
        });

        this.getChildren().addAll(etiqueta, botonEntrar, botonSalir);
    }
}
