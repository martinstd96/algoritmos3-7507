package com.company.vista.gui;

import com.company.vista.gui.eventos.CambiarTurnoHandler;
import com.company.vista.terreno.MapaView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContenedorPrincipal extends BorderPane {

    BarraDeMenu menuBar;
    MapaView mapa;

    public ContenedorPrincipal(Stage stage){
        MapaView mapa = MapaView.getMapa();
        setCenter(mapa);
        setMenu(stage);
        setBotonera();
    }

    private void setMenu(Stage stage) {
        this.menuBar = new BarraDeMenu(stage);
        this.setTop(menuBar);
    }

    private void setBotonera() {

        Button botonCambiarTurno = new Button("Pasar turno");
        botonCambiarTurno.setOnAction(new CambiarTurnoHandler());

        GeneradorDeBotones generadorDeBotones = GeneradorDeBotones.getGenerador();
        generadorDeBotones.generarBotonPasarTurno();

    }
}
