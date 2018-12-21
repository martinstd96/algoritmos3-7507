package com.company.vista.terreno.entidades;

import com.company.DTO.EntidadDTO;
import com.company.vista.gui.eventos.selecciones.GestorDeSelecciones;
import com.company.vista.terreno.MapaView;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.logging.Logger;

public class CasilleroView extends Rectangle {

    private Integer columna;
    private Integer fila;
    private String nombre = "";
    private MapaView mapa;
    private Logger logger = Logger.getLogger(this.getClass().toString());

    public CasilleroView(Integer x, Integer y, Float dimension, MapaView mapa, EntidadDTO entidad){
        this.mapa = mapa;
        this.columna = x;
        this.fila = y;
        this.setWidth(dimension);
        this.setHeight(dimension);

        if(entidad == null){
            setFill(Color.GREEN);
        } else {
            if(entidad.getJugador() == 1){
                setFill(Color.ORANGE);
            } else {
                setFill(Color.SKYBLUE);
            }
            nombre = entidad.getNombre();
        }

        CasilleroView casillero = this;
        this.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                Integer x = casillero.getColumna();
                Integer y = casillero.getFila();
                if(t.getButton() == MouseButton.SECONDARY){
                    GestorDeSelecciones.seleccionarCasilleroDestino(x, y);
                } else {
                    GestorDeSelecciones.seleccionarCasilleroOrigen(x, y);
                }
            }
        });
    }

    public Integer getColumna(){
        return this.columna;
    }

    public Integer getFila(){
        return this.fila;
    }

    public void mostrarEtiqueta(){
        mapa.add(new Label(nombre), this.getColumna(), this.getFila());
    }
}
