package com.company.vista.gui.eventos.selecciones;

import com.company.DTO.Accion;
import com.company.DTO.EntidadDTO;
import com.company.DTO.JugadorDTO;
import com.company.controlador.Controlador;
import com.company.excepciones.ArmaDesmontadaException;
import com.company.excepciones.ArmaMontadaException;
import com.company.excepciones.CastilloDestruidoExcecption;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.vista.gui.GeneradorDeBotones;
import com.company.vista.gui.eventos.OpcionSalirEventHandler;
import com.company.vista.gui.eventos.ejecutadores.Movedor;
import com.company.vista.terreno.MapaView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

public class GestorDeSelecciones {

    private static Controlador controlador = Controlador.getControlador();
    private static Integer xDelCasilleroDeOrigen;
    private static Integer yDelCasilleroDeOrigen;
    private static Accion accionAEjecutar = null;
    private static GeneradorDeBotones generadorDeBotones = GeneradorDeBotones.getGenerador();

    public static void seleccionarCasilleroOrigen(Integer x, Integer y){
        EntidadDTO entidad= controlador.buscarContenidoDelCasillero(x, y);

        if(entidad != null){
            entidad.generarBotones();
        } else {
            generadorDeBotones.limpiarBotonera();
        }
        xDelCasilleroDeOrigen = x;
        yDelCasilleroDeOrigen = y;
        accionAEjecutar = new Accion("", new Movedor());
    }

    public static void seleccionarAccion(Accion accion){
        accionAEjecutar = accion;
    }

    public static void seleccionarCasilleroDestino(Integer x, Integer y) {
        try {
            accionAEjecutar.llamarAccion(xDelCasilleroDeOrigen, yDelCasilleroDeOrigen, x, y);
            pasarTurno();
        } catch (NullPointerException npe){
            if(xDelCasilleroDeOrigen == -1){
                System.out.println("No se selecciono ningun casillero");
            }
            return;
        } catch (Exception | ArmaMontadaException | EdificioNoDisponibleException | ArmaDesmontadaException | EdificioEnConstruccionException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(e.getMessage());
            alert.show();

            String archivoDeMusica = "error.mp3";
            Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
            MediaPlayer reproductorError = new MediaPlayer(archivo);
            reproductorError.setAutoPlay(true);

        } catch (CastilloDestruidoExcecption e){
            JugadorDTO jugador = controlador.obtenerJugadorActual();
            Integer jugadorActual = jugador.getNumeroDeJugador() - 1;

            if(jugadorActual == 0) jugadorActual = 2;

            Button botonSalir = new Button();
            botonSalir.setText("Salir");
            botonSalir.setFont(Font.font("Lucida Console", 25));
            botonSalir.setTranslateX(200);
            botonSalir.setStyle("-fx-base: #394867; -fx-border-color: rgb(150,180,189)");
            OpcionSalirEventHandler botonSalirHandler = new OpcionSalirEventHandler();
            botonSalir.setOnAction(botonSalirHandler);

            Label label = new Label("Ha ganado la partida!");
            label.setFont(Font.font("Lucida Console",30));
            label.setMinSize(200, 60);
            label.setTranslateX(65);

            Label secondLabel = new Label("Toque salir para continuar");
            secondLabel.setFont(Font.font("Lucida Console",20));
            secondLabel.setMinSize(200, 60);
            secondLabel.setTranslateX(80);

            VBox layout = new VBox();
            layout.getChildren().addAll(label, secondLabel, botonSalir);

            Scene contenedor = new Scene(layout,500, 250);

            Stage ventanaGanador = new Stage();
            ventanaGanador.setTitle(String.format("Felicidades jugador %d!", jugadorActual));
            ventanaGanador.setScene(contenedor);
            ventanaGanador.show();

            String archivoDeMusica = "victory.mp3";
            Media archivo = new Media(new File(archivoDeMusica).toURI().toString());
            MediaPlayer reproductorError = new MediaPlayer(archivo);
            reproductorError.setAutoPlay(true);
        }
    }

    private static void reiniciarOpciones(){
        xDelCasilleroDeOrigen = -1;
        yDelCasilleroDeOrigen = -1;
        accionAEjecutar = null;
        generadorDeBotones.limpiarBotonera();
    }

    public static void pasarTurno() {
        reiniciarOpciones();
        MapaView.getMapa().actualizarCasilleros();
    }
}
