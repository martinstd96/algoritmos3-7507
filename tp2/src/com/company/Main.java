package com.company;

import com.company.controlador.Controlador;
import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.modelo.Partida;
import com.company.vista.gui.ContenedorPrincipal;
import com.company.vista.gui.GeneradorDeBotones;
import com.company.vista.gui.PantallaInicial;
import com.company.vista.terreno.MapaView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws CasilleroNoExistenteException, CasilleroLlenoException {
        primaryStage.setTitle("Algo of Empires II");

        Partida partida = new Partida();

        GeneradorDeBotones generadorDeBotones =  GeneradorDeBotones.getGenerador();
        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(primaryStage);
        generadorDeBotones.establecerContenedor(contenedorPrincipal);

        Scene escenaMapa = new Scene(contenedorPrincipal, 1200, 600);
        Scene pantallaInicial = new Scene (new PantallaInicial(primaryStage,escenaMapa));
        primaryStage.setScene(pantallaInicial);

        primaryStage.show();

        MapaView mapa = MapaView.getMapa();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}
