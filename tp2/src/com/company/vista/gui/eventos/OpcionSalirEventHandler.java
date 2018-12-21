package com.company.vista.gui.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.logging.Logger;

public class OpcionSalirEventHandler implements EventHandler<ActionEvent> {

    private Logger logger = Logger.getLogger(this.getClass().toString());

    @Override
    public void handle(ActionEvent actionEvent) {
        logger.info("Programa cerrado por usuario");
        System.exit(0);
    }
}