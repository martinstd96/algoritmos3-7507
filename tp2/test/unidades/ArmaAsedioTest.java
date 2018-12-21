package unidades;

import com.company.excepciones.*;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.Castillo;
import com.company.modelo.edificios.Cuartel;
import com.company.modelo.edificios.PlazaCentral;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.modelo.unidades.Arquero;
import com.company.modelo.unidades.Espadachin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArmaAsedioTest {

    private Mapa mapa = Mapa.getMapa();
    Jugador jugador = null;
    Jugador jugadorEnemigo = null;
    Aldeano aldeanoEnemigo = null;
    Castillo castilloEnemigo = null;
    Cuartel cuartelEnemigo = null;
    ArmaAsedio maquinaAsedio = null;
    ArmaAsedio maquinaAsedio2 = null;
    PlazaCentral plazaEnemiga = null;
    Arquero arqueroEnemigo = null;
    Espadachin espadachinEnemigo = null;


    @Before
    public void resetMapa() {
        jugador = new Jugador();
        jugadorEnemigo = new Jugador();
        mapa.destruir();
        mapa = Mapa.getMapa();
        aldeanoEnemigo = new Aldeano(jugadorEnemigo);
        castilloEnemigo = new Castillo(jugadorEnemigo);
        cuartelEnemigo = new Cuartel(jugadorEnemigo);
        maquinaAsedio = new ArmaAsedio(jugador);
        plazaEnemiga = new PlazaCentral(jugadorEnemigo);
        maquinaAsedio2 = new ArmaAsedio(jugadorEnemigo);
        espadachinEnemigo = new Espadachin(jugadorEnemigo);
        arqueroEnemigo = new Arquero(jugadorEnemigo);
    }

    @Test
    public void testAsedioMoverUnCasilleroALaDerecha() throws Exception, EdificioNoDisponibleException, ArmaMontadaException {


        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);

        //arma de asedio se posiciona en 7 6
        assertTrue(mapa.estaOcupado(7, 6));

        maquinaAsedio.moverA(8, 6);


        assertTrue(mapa.estaOcupado(8, 6));
        assertFalse(mapa.estaOcupado(7, 6));

    }

    @Test
    public void testAsedioMoverHorizontalmenteHaciaAtras() throws Exception, ArmaMontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);

        assertTrue(mapa.estaOcupado(7, 6));

        maquinaAsedio.moverA(8, 6);
        maquinaAsedio.moverA(7, 6);

        assertTrue(mapa.estaOcupado(7, 6));
        assertFalse(mapa.estaOcupado(8, 6));

    }

    @Test
    public void testAsedioMoverVerticalmenteHaciaArriba() throws Exception, ArmaMontadaException {


        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);

        assertTrue(mapa.estaOcupado(7, 6));
        maquinaAsedio.moverA(7, 7);

        assertTrue(mapa.estaOcupado(7, 7));
        assertFalse(mapa.estaOcupado(7, 6));
    }

    @Test
    public void testAsedioMoverVerticalmenteHaciaAbajo() throws Exception, ArmaMontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);

        assertTrue(mapa.estaOcupado(7, 6));

        maquinaAsedio.moverA(7, 5);

        assertTrue(mapa.estaOcupado(7, 5));
        assertFalse(mapa.estaOcupado(7, 6));
    }

    @Test
    public void testAsedioMoverEnDiagonalHaciaArribaALaIzquierda()
            throws Exception, ArmaMontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);
        assertTrue(mapa.estaOcupado(7, 6));

        maquinaAsedio.moverA(8, 6);
        maquinaAsedio.moverA(7, 7);

        assertTrue(mapa.estaOcupado(7, 7));
        assertFalse(mapa.estaOcupado(8, 6));
    }

    @Test
    public void testAsedioMoverEnDiagonalHaciaArribaALaDerecha() throws Exception
            , ArmaMontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);
        assertTrue(mapa.estaOcupado(7, 6));

        maquinaAsedio.moverA(8, 7);

        assertTrue(mapa.estaOcupado(8, 7));
        assertFalse(mapa.estaOcupado(7, 6));
    }

    @Test
    public void testAsedioMoverEnDiagonalHaciaAbajoALaIzquierda()
            throws Exception, ArmaMontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);

        assertTrue(mapa.estaOcupado(7, 6));

        maquinaAsedio.moverA(8, 6);
        maquinaAsedio.moverA(7, 5);

        assertTrue(mapa.estaOcupado(7, 5));
        assertFalse(mapa.estaOcupado(8, 6));
    }

    @Test
    public void testAsedioMoverEnDiagonalHaciaAbajoALaDerecha() throws Exception, ArmaMontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);
        assertTrue(mapa.estaOcupado(7, 6));

        maquinaAsedio.moverA(8, 5);

        assertTrue(mapa.estaOcupado(8, 5));
        assertFalse(mapa.estaOcupado(7, 6));

    }

    @Test(expected = ArmaMontadaException.class)
    public void testAsedioMoverVerticalementeHaciaArribaMontada()
            throws Exception, ArmaMontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);

        assertTrue(mapa.estaOcupado(7, 6));
        maquinaAsedio.montar();
        maquinaAsedio.moverA(7, 7);

        assertFalse(mapa.estaOcupado(7, 7));
        assertTrue(mapa.estaOcupado(7, 6));


    }

    @Test(expected = MovimientoInvalidoException.class)
    public void testAsedioMoverHorizontalmenteHaciaLaIzquierdaAUnCasilleroOcupado()
            throws Exception, ArmaMontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(7, 6);
        mapa.ubicar(maquinaAsedio, 7, 6);

        maquinaAsedio2.establecerCoordenadasDeNacimiento(6, 6);
        mapa.ubicar(maquinaAsedio2, 6, 6);

        assertTrue(mapa.estaOcupado(7, 6));
        maquinaAsedio.moverA(6, 6);

        assertFalse(mapa.estaOcupado(6, 6));
        assertTrue(mapa.estaOcupado(7, 6));

    }

    @Test
    public void testArmaAsedioAtacarAEdificiosEnemigosConDistanciaIgualACinco()
            throws Exception, ArmaMontadaException, ArmaDesmontadaException {

        maquinaAsedio.establecerCoordenadasDeNacimiento(10, 10);

        mapa.ubicar(maquinaAsedio, 10, 10);

        maquinaAsedio.montar();

        castilloEnemigo.surgir(15, 10);

        plazaEnemiga.surgir(4, 10);

        cuartelEnemigo.construir(aldeanoEnemigo, 10, 4);
        cuartelEnemigo.avanzarConstruccion();
        cuartelEnemigo.avanzarConstruccion();
        cuartelEnemigo.avanzarConstruccion();

        maquinaAsedio.atacarA(castilloEnemigo);

        maquinaAsedio.atacarA(plazaEnemiga);
        maquinaAsedio.atacarA(cuartelEnemigo);

        assertEquals((Integer) 925, castilloEnemigo.getVida());
        assertEquals((Integer) 175, cuartelEnemigo.getVida());
        assertEquals((Integer) 375, plazaEnemiga.getVida());

    }

   @Test
    public void testArmaAsedioAtacarUnidadesEnemigasEsInvalido()
            throws CasilleroNoExistenteException,
            CasilleroLlenoException, ArmaMontadaException{

        maquinaAsedio.establecerCoordenadasDeNacimiento(10, 10);
        maquinaAsedio.ubicar(10,10);
        maquinaAsedio.montar();

        aldeanoEnemigo.establecerCoordenadasDeNacimiento(9,9);
        aldeanoEnemigo.ubicar(9,9);

        espadachinEnemigo.establecerCoordenadasDeNacimiento(8,8);
        espadachinEnemigo.ubicar(8,8);

        arqueroEnemigo.establecerCoordenadasDeNacimiento(11,11);
        arqueroEnemigo.ubicar(11,11);

        try {
            maquinaAsedio.atacarA(aldeanoEnemigo);
        }catch(EnemigoInvalidoException ignored){ }

       try {

           maquinaAsedio.atacarA(espadachinEnemigo);

       }catch(EnemigoInvalidoException ignored){ }

       try {

           maquinaAsedio.atacarA(arqueroEnemigo);
       }catch(EnemigoInvalidoException ignored){ }


        assertEquals((Integer) 50, aldeanoEnemigo.getVida());
        assertEquals((Integer) 100, espadachinEnemigo.getVida());
        assertEquals((Integer) 75, arqueroEnemigo.getVida());
    }
}