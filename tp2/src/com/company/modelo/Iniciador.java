package com.company.modelo;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.modelo.edificios.Castillo;
import com.company.modelo.edificios.PlazaCentral;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;

public class Iniciador {

    private Jugador jugador;
    private static Integer cantidadDeJugadores = 0;
    Mapa mapa = Mapa.getMapa();

    public Iniciador(Jugador unJugador){
        this.jugador = unJugador;
        cantidadDeJugadores += 1;
    }

    public void crearEntidadesIniciales() throws CasilleroNoExistenteException, CasilleroLlenoException {
        Integer posicionInicialX;
		Integer posicionInicialY;

		if(cantidadDeJugadores == 1){
			posicionInicialX = 5;
			posicionInicialY = 5;
		} else {
			posicionInicialX = 18;
			posicionInicialY = 18;
		}

        Castillo castillo = new Castillo(this.jugador);
        PlazaCentral plaza = new PlazaCentral(this.jugador);

        castillo.surgir(posicionInicialX, posicionInicialY);
        plaza.surgir(posicionInicialX , (posicionInicialY + 5) );

        Aldeano miPrimerAldeano = new Aldeano(this.jugador);
        Aldeano miSegundoAldeano = new Aldeano(this.jugador);
        Aldeano miTercerAldeano = new Aldeano((this.jugador));

        miPrimerAldeano.establecerCoordenadasDeNacimiento( (posicionInicialX - 1), (posicionInicialY + 2) );
        mapa.ubicar(miPrimerAldeano, (posicionInicialX - 1), (posicionInicialY + 2) );

        miSegundoAldeano.establecerCoordenadasDeNacimiento( (posicionInicialX + 2), (posicionInicialY - 2) );
        mapa.ubicar(miSegundoAldeano, (posicionInicialX + 2), (posicionInicialY - 2) );

        miTercerAldeano.establecerCoordenadasDeNacimiento( (posicionInicialX + 3), (posicionInicialY + 4) );
        mapa.ubicar(miTercerAldeano, (posicionInicialX + 3), (posicionInicialY + 4) );

        jugador.agregarAPoblacion(miPrimerAldeano);
        jugador.agregarAPoblacion(miSegundoAldeano);
        jugador.agregarAPoblacion(miTercerAldeano);
    }
}
