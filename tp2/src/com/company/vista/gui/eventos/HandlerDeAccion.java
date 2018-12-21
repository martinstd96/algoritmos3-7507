package com.company.vista.gui.eventos;

import com.company.DTO.Accion;
import com.company.vista.gui.eventos.selecciones.GestorDeSelecciones;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandlerDeAccion implements EventHandler<ActionEvent> {

    private Accion accion;

    public void setearAccion(Accion nuevaAccion){
        this.accion = nuevaAccion;
    }

    @Override
    public void handle(ActionEvent event) {
        GestorDeSelecciones.seleccionarAccion(accion);
    }
}
