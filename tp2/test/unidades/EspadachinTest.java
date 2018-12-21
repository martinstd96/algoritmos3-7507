package unidades;

import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.Cuartel;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Espadachin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EspadachinTest {

    Mapa mapa = Mapa.getMapa();
    Jugador jugador = null;
    Espadachin espadachin = null;
    Cuartel cuartel = null;
    Aldeano peon = null;

    @Before
    public void resetMapa() {
        jugador = new Jugador();
        mapa.destruir();
        mapa = Mapa.getMapa();
        espadachin = new Espadachin(jugador);
        cuartel = new Cuartel(jugador);
        peon = new Aldeano(jugador);
    }

    @Test
    public void testMoverHorizontalmenteHaciaLaIzquierda() throws CasilleroNoExistenteException, CasilleroLlenoException, ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(6, 6);

        assertTrue(mapa.estaOcupado(6, 6));
    }

    @Test
    public void testMoverHorizontalmenteHaciaLaDerecha() throws CasilleroNoExistenteException, CasilleroLlenoException, ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(6, 6);
        espadachin.moverA(5, 6);

        assertTrue(mapa.estaOcupado(5, 6));
        assertFalse(mapa.estaOcupado(6, 6));

    }

    @Test
    public void testMoverVerticalmenteHaciaArriba() throws CasilleroNoExistenteException, CasilleroLlenoException, ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(5, 7);

        assertTrue(mapa.estaOcupado(5, 7));
        assertFalse(mapa.estaOcupado(5, 6));
    }

    @Test
    public void testMoverVerticalmenteHaciaAbajo()
            throws CasilleroNoExistenteException, CasilleroLlenoException,
            ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);
        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(5, 5);

        assertTrue(mapa.estaOcupado(5, 5));
    }

    @Test
    public void testMoverEnDiagonalHaciaArribaALaIzquierda()
            throws CasilleroNoExistenteException, CasilleroLlenoException, ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(4, 7);
        assertTrue(mapa.estaOcupado(4, 7));
        assertFalse(mapa.estaOcupado(5, 6));
    }

    @Test
    public void testMoverEnDiagonalHaciaArribaALaDerecha()
            throws CasilleroNoExistenteException, CasilleroLlenoException,
            ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(5, 7);

        assertTrue(mapa.estaOcupado(5, 7));
        assertFalse(mapa.estaOcupado(5, 6));
    }

    @Test
    public void testMoverEnDiagonalHaciaAbajoALaIzquierda()
            throws CasilleroNoExistenteException, CasilleroLlenoException,
            ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(6, 6);
        espadachin.moverA(5, 5);

        assertTrue(mapa.estaOcupado(5, 5));
        assertFalse(mapa.estaOcupado(6, 6));
    }

    @Test
    public void testMoverEnDiagonalHaciaAbajoALaDerecha()
            throws CasilleroNoExistenteException, CasilleroLlenoException,
            ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(6, 5);

        assertTrue(mapa.estaOcupado(6, 5));
    }

    @Test(expected = MovimientoInvalidoException.class)
    public void testHorizontalmenteALaIzquierdaACasilleroOcupado()
            throws CasilleroNoExistenteException, CasilleroLlenoException, ArmaMontadaException, MovimientoInvalidoException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        Espadachin espadachin2 = new Espadachin(jugador);
        espadachin2.establecerCoordenadasDeNacimiento(4, 6);
        mapa.ubicar(espadachin2, 4, 6);

        assertTrue(mapa.estaOcupado(5, 6));

        espadachin.moverA(4, 6);

        assertTrue(mapa.estaOcupado(5, 6));
    }

    @Test
    public void testEspadachinAtacarAUnEdificioCercanoEnemigo() throws Exception {

        Jugador otroJugador = new Jugador();
        cuartel = new Cuartel(otroJugador);

        Espadachin otro = new Espadachin(jugador);

        cuartel.construir(peon, 5, 2);

        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();

        espadachin.establecerCoordenadasDeNacimiento(5,1);
        espadachin.ubicar(5,1);

        try {
            espadachin.atacarA(cuartel);
        } catch (ArmaDesmontadaException e) { }

        assertEquals((Integer) 235, cuartel.getVida());

    }

    @Test
    public void testEspadachinAtacarAUnaUnidadCercanaAmiga() throws Exception, ArmaDesmontadaException {

        espadachin.establecerCoordenadasDeNacimiento(5, 6);
        mapa.ubicar(espadachin, 5, 6);

        Espadachin otroEspadachin = new Espadachin(jugador);
        otroEspadachin.establecerCoordenadasDeNacimiento(4, 6);
        mapa.ubicar(otroEspadachin, 4, 6);

        espadachin.atacarA(otroEspadachin);

        assertEquals( (Integer)75, otroEspadachin.getVida() );

    }

    @Test(expected = EnemigoInvalidoException.class)
    public void testEspadachinAtacarAUnaUnidadAmiga() throws Exception, EdificioDestruidoExcepcion, EdificioEnConstruccionException, com.company.excepciones.Edificio.EdificioNoDisponibleException, ArmaDesmontadaException {

        Jugador jugador = new Jugador();
        Cuartel cuartel = new Cuartel(jugador);
        Aldeano peon = new Aldeano(jugador);
        Espadachin espadachin = new Espadachin(jugador);

        cuartel.construir(peon, 12, 9);
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();

        cuartel.crear(espadachin);

        espadachin.atacarA(cuartel);

        assertEquals((Integer) 250, cuartel.getVida());
    }

    @Test
    public void testEspadachinAtacarADosPosicionablesEnemigos()
            throws Exception, ArmaDesmontadaException {

        Jugador otroJugador = new Jugador();

        cuartel = new Cuartel(otroJugador);

        Espadachin otroEspadachin = new Espadachin(otroJugador);

        cuartel.construir(peon, 12, 9);
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();

        otroEspadachin.establecerCoordenadasDeNacimiento(13,7);
        otroEspadachin.ubicar(13,7);

        espadachin.establecerCoordenadasDeNacimiento(14,8);
        espadachin.ubicar(14,8);

        espadachin.atacarA(otroEspadachin);
        espadachin.atacarA(cuartel);

       // assertEquals((Integer) 75, otroEspadachin.getVida());
        assertEquals((Integer) 235, cuartel.getVida());
    }

    @Test(expected = EnemigoInvalidoException.class)
    public void testEspadachinAtacarAUnEdificioEnemigoFueraDeSuRangoDeAtaque()
            throws Exception,EdificioNoDisponibleException, ArmaDesmontadaException {

        cuartel = new Cuartel (new Jugador());
        cuartel.construir(peon, 2, 10);
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();
        cuartel.avanzarConstruccion();

        cuartel.crear(espadachin);

        espadachin.establecerCoordenadasDeNacimiento(2,6);
        espadachin.ubicar(2,6);

        espadachin.atacarA(cuartel);


        assertEquals((Integer) 250, cuartel.getVida());
    }
}
