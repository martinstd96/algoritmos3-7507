package com.company.vista.gui;

import com.company.vista.gui.eventos.OpcionSalirEventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;

public class BarraDeMenu extends MenuBar {

    public BarraDeMenu(Stage stage) {

        Menu menuArchivo = new Menu("Archivo");
        MenuItem opcionSalir = new MenuItem("Salir");
        OpcionSalirEventHandler opcionSalirHandler = new OpcionSalirEventHandler();
        opcionSalir.setOnAction(opcionSalirHandler);


        menuArchivo.getItems().addAll(opcionSalir);

        this.getMenus().addAll(menuArchivo);
    }
}