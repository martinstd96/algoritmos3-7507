package com.company.vista.gui;

import com.company.DTO.Accion;
import com.company.DTO.JugadorDTO;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.modelo.unidades.Unidad;
import com.company.vista.gui.eventos.CambiarTurnoHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class GeneradorDeBotones {

    private static GeneradorDeBotones instancia;
    private ContenedorPrincipal contenedorPrincipal;
    private VBox controlesIzquierdos = new VBox();
    private VBox informacionDerecha = new VBox();


    private GeneradorDeBotones(){
        controlesIzquierdos.setSpacing(10);
        controlesIzquierdos.setPadding(new Insets(15));
    }

    public static GeneradorDeBotones getGenerador() {
        if(instancia == null){
            instancia = new GeneradorDeBotones();
        }
        return instancia;
    }

    public void establecerContenedor(ContenedorPrincipal contenedorPrincipal) {
        this.contenedorPrincipal = contenedorPrincipal;
        contenedorPrincipal.setLeft(controlesIzquierdos);
        contenedorPrincipal.setRight(informacionDerecha);
    }

    private Label getLabel(){
        Label etiqueta = new Label();
        etiqueta.setFont(Font.font("Tahoma", FontWeight.BOLD, 18));
        etiqueta.setText("Controles");
        etiqueta.setTextFill(Color.web("#66A7C5"));
        return etiqueta;
    }

    public void generarBotones(ArrayList<Accion> acciones) {
        controlesIzquierdos.getChildren().clear();
        generarBotonPasarTurno();
        controlesIzquierdos.getChildren().add(getLabel());
        for(Accion accion :acciones){
            Button boton = generarBoton(accion);
            controlesIzquierdos.getChildren().add(boton);
        }
    }

    public Button generarBoton(Accion accion){
        Button boton = new Button();
        boton.setText(accion.getDescripcion());
        boton.setOnAction(accion.getHandlerDelBoton());
        boton.setStyle("-fx-base: #394867");
        boton.setTextFill(Color.WHITE);
        return boton;
    }

    public void limpiarBotonera(){
        controlesIzquierdos.getChildren().clear();
        contenedorPrincipal.setLeft(controlesIzquierdos);
        generarBotonPasarTurno();
    }

    public void generarBotonPasarTurno(){
        Button botonCambiarTurno = new Button("~ ~ Pasar este turno ~ ~");
        botonCambiarTurno.setOnAction(new CambiarTurnoHandler());
        controlesIzquierdos.getChildren().add(botonCambiarTurno);
    }

    private Label conseguirLabelInfoJugador(String info){
        Label etiqueta = new Label();
        etiqueta.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
        etiqueta.setText(info);
        etiqueta.setTextFill(Color.web("#66A7C5"));
        return etiqueta;
    }

    private void mostrarInformacionDePoblacion(JugadorDTO jugador){

        for( Unidad unaUnidad : jugador.getPoblacion() ){
            Integer vidaMaxima = unaUnidad.getVidaMaxima();
            Integer vidaActual = unaUnidad.getVida();
            String nombreUnidad = unaUnidad.getClass().getSimpleName();
            String formato = String.format("%s: %d/%d", nombreUnidad, vidaActual, vidaMaxima);
            Label unidad = conseguirLabelInfoJugador(formato);
            informacionDerecha.getChildren().add(unidad);
        }

    }

    private void mostrarInformacionDeEdificios(JugadorDTO jugador) {

        for( Edificio unEdificio : jugador.getEdificios() ){
            Integer vidaMaxima = unEdificio.getVidaMaxima();
            Integer vidaActual = 0;
            try {
                vidaActual = unEdificio.getVida();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String nombreEdificio = unEdificio.getClass().getSimpleName();
            String formato = String.format("%s: %d/%d", nombreEdificio, vidaActual, vidaMaxima);
            Label edificio = conseguirLabelInfoJugador(formato);
            informacionDerecha.getChildren().add(edificio);
        }

    }

    public void mostrarInformacion(JugadorDTO jugador){
        informacionDerecha.getChildren().clear();
        Label numero = conseguirLabelInfoJugador("Turno del jugador " + jugador.getNumeroDeJugador());
        Label poblacion = conseguirLabelInfoJugador("Poblacion: " + jugador.getPoblacionCorriente() + "/" + jugador.getPoblacionMaxima());
        Label oro = conseguirLabelInfoJugador("Oro: " + jugador.getOro());
        informacionDerecha.getChildren().addAll(numero, poblacion, oro);
        mostrarInformacionDePoblacion(jugador);
        mostrarInformacionDeEdificios(jugador);
    }
}
