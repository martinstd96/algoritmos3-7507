package unidades;

import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.Cuartel;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Arquero;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArqueroTest {

    Mapa mapa = Mapa.getMapa();
    Jugador jugador = null;
    Cuartel cuartel = null;
    Aldeano peon = null;
    Arquero arquero = null;

    @Before
    public void resetMapa() {
        jugador = new Jugador();
        mapa.destruir();
        mapa = Mapa.getMapa();
        cuartel = new Cuartel(jugador);
        peon = new Aldeano(jugador);
        arquero = new Arquero(jugador);
    }

    @Test
    public void testArqueroMoverHorizontalmenteHaciaDelante() throws
            Exception,
            ArmaMontadaException {

        arquero.establecerCoordenadasDeNacimiento(7, 5);

        arquero.ubicar(7, 5);

        assertTrue(mapa.estaOcupado(7, 5));

        arquero.moverA(8, 5);

        assertTrue(mapa.estaOcupado(8, 5));
        assertFalse(mapa.estaOcupado(7, 5));
    }

    @Test
    public void testArqueroMoverHorizontalmenteHaciaAtras()
            throws Exception, ArmaMontadaException {

        arquero.establecerCoordenadasDeNacimiento(5, 5);

        arquero.ubicar(5, 5);

        assertTrue(mapa.estaOcupado(5, 5));

        arquero.moverA(5, 4);

        assertFalse(mapa.estaOcupado(5, 5));

    }

    @Test
    public void testArqueroMoverVerticalmenteHaciaArriba()
            throws Exception, ArmaMontadaException {

        arquero.establecerCoordenadasDeNacimiento(4, 10);

        arquero.ubicar(4, 10);

        assertTrue(mapa.estaOcupado(4, 10));

        arquero.moverA(4, 11);

        assertTrue(mapa.estaOcupado(4, 11));
        assertFalse(mapa.estaOcupado(4, 10));
    }

    @Test
    public void testArqueroMoverVerticalmenteHaciaAbajo()
            throws Exception, ArmaMontadaException {


        arquero.establecerCoordenadasDeNacimiento(22, 9);

        arquero.ubicar(22, 9);

        assertTrue(mapa.estaOcupado(22, 9));

        arquero.moverA(22, 8);

        assertTrue(mapa.estaOcupado(22, 8));
        assertFalse(mapa.estaOcupado(22, 9));
    }

    @Test
    public void testArqueroMoverEnDiagonalHaciaArribaALaIzquierda()
            throws Exception, ArmaMontadaException {


        arquero.establecerCoordenadasDeNacimiento(19, 17);

        arquero.ubicar(19, 17);

        assertTrue(mapa.estaOcupado(19, 17));

        arquero.moverA(18, 18);

        assertTrue(mapa.estaOcupado(18, 18));
    }

    @Test
    public void testArqueroMoverEnDiagonalHaciaArribaALaDerecha()
            throws Exception, EdificioNoDisponibleException, ArmaMontadaException {

        arquero.establecerCoordenadasDeNacimiento(14, 9);

        arquero.ubicar(14, 9);

        assertTrue(mapa.estaOcupado(14, 9));

        arquero.moverA(15, 10);

        assertTrue(mapa.estaOcupado(15, 10));
        assertFalse(mapa.estaOcupado(14, 9));
    }

    @Test
    public void testArqueroMoverEnDiagonalHaciaAbajoALaIzquierda()
            throws Exception, ArmaMontadaException {

        arquero.establecerCoordenadasDeNacimiento(5, 5);

        arquero.ubicar(5, 5);

        assertTrue(mapa.estaOcupado(5, 5));

        arquero.moverA(4, 4);

        assertTrue(mapa.estaOcupado(4, 4));
        assertFalse(mapa.estaOcupado(5, 5));
    }

    @Test
    public void testArqueroMoverEnDiagonalHaciaAbajoALaDerecha()
            throws Exception, EdificioNoDisponibleException, ArmaMontadaException {

        arquero.establecerCoordenadasDeNacimiento(4, 2);

        arquero.ubicar(4, 2);

        assertTrue(mapa.estaOcupado(4, 2));

        arquero.moverA(5, 1);

        assertTrue(mapa.estaOcupado(5, 1));
        assertFalse(mapa.estaOcupado(4, 2));
    }

    @Test
    public void testArqueroAtacarAUnEdificioCercanoEnemigoConDistanciaIgualATres()
            throws Exception {

        cuartel = new Cuartel(new Jugador());

        cuartel.construir(peon, 2, 4);

        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();


        arquero.establecerCoordenadasDeNacimiento(1, 5);

        arquero.ubicar(1, 5);

        try {
            arquero.atacarA(cuartel);
        } catch (ArmaDesmontadaException ignored) {
        }

        assertEquals((Integer) 240, cuartel.getVida());

    }

    @Test
    public void testArqueroAtacarAUnaUnidadCercanaEnemigaConDistanciaIgualADos() {

        arquero.establecerCoordenadasDeNacimiento(2, 1);

        try {
            arquero.ubicar(2, 1);
        } catch (CasilleroNoExistenteException
                | CasilleroLlenoException e) {
        }

        Arquero otroArquero = new Arquero(jugador);

        otroArquero.establecerCoordenadasDeNacimiento(0, 3);

        try {
            otroArquero.ubicar(0, 3);
        } catch (CasilleroNoExistenteException
                | CasilleroLlenoException e) {
        }

        try {
            arquero.atacarA(otroArquero);
        } catch (EnemigoInvalidoException | ArmaDesmontadaException e) {
        }

        assertEquals((Integer) 60, otroArquero.getVida());

    }

    @Test
    public void testArqueroAtacarAUnaUnidadEnemigaConDistanciaIgualAUno()
            throws Exception {

        cuartel = new Cuartel(new Jugador());

        cuartel.construir(peon, 12, 9);

        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();

        arquero.establecerCoordenadasDeNacimiento(12, 8);

        arquero.ubicar(12, 8);

        try {
            arquero.atacarA(cuartel);
        } catch (ArmaDesmontadaException ignored) {
        }

        assertEquals((Integer) 240, cuartel.getVida());
    }

    @Test
    public void testArqueroAtacarADosPosicionablesEnemigosConDistanciasDistintas()
            throws Exception, ArmaDesmontadaException {

        Jugador jugador2 = new Jugador();

        Arquero otroArquero = new Arquero(jugador2);

        otroArquero.establecerCoordenadasDeNacimiento(5, 5);
        otroArquero.ubicar(5, 5);

        arquero.establecerCoordenadasDeNacimiento(5, 4);
        arquero.ubicar(5, 4);

        cuartel = new Cuartel(jugador2);

        cuartel.construir(peon, 8, 5);

        cuartel.avanzarConstruccion();

        cuartel.avanzarConstruccion();

        cuartel.avanzarConstruccion();

        arquero.atacarA(otroArquero);
        arquero.atacarA(cuartel);

        assertEquals((Integer) 60, otroArquero.getVida());
        assertEquals((Integer) 240, cuartel.getVida());
    }


    @Test(expected = EnemigoInvalidoException.class)
    public void testArqueroAtacarAUnEdificioEnemigoFueraDeSuRangoDeAtaque()
            throws Exception, ArmaDesmontadaException {

        Jugador otroJugador = new Jugador();

        Cuartel otroCuartel = new Cuartel(otroJugador);

        Aldeano otroPeon = new Aldeano(otroJugador);

        arquero.establecerCoordenadasDeNacimiento(2, 10);
        arquero.ubicar(2, 10);

        otroCuartel.construir(otroPeon, 0, 0);
        otroCuartel.avanzarConstruccion();
        otroCuartel.avanzarConstruccion();
        otroCuartel.avanzarConstruccion();

        arquero.atacarA(otroCuartel);

        assertEquals((Integer) 250, otroCuartel.getVida());
    }

}
