package com.company.DTO;

import com.company.excepciones.ArmaDesmontadaException;
import com.company.excepciones.ArmaMontadaException;
import com.company.excepciones.CastilloDestruidoExcecption;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.vista.gui.eventos.HandlerDeAccion;
import com.company.vista.gui.eventos.ejecutadores.Ejecutador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Accion {

    private String descripcion;
    private HandlerDeAccion handlerDelBoton;
    private Ejecutador ejecutador;

    public Accion(String descripcion, Ejecutador ejecutador){
        this.descripcion = descripcion;
        this.handlerDelBoton = new HandlerDeAccion();
        this.ejecutador = ejecutador;
        handlerDelBoton.setearAccion(this);
    }

    public String getDescripcion(){
        return descripcion;
    }

    public EventHandler<ActionEvent> getHandlerDelBoton(){
        return handlerDelBoton;
    }

    public Ejecutador getEjecutador(){
        return ejecutador;
    }

    public void llamarAccion(Integer xOrigen, Integer yOrigen, Integer xDestino, Integer yDestino) throws ArmaMontadaException, EdificioNoDisponibleException, ArmaDesmontadaException, Exception, EdificioEnConstruccionException, CastilloDestruidoExcecption {
        ejecutador.ejecutar(xOrigen, yOrigen, xDestino, yDestino);
    }

    public void llamarAccion(){

    }
}
