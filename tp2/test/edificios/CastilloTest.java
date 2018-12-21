package edificios;

import com.company.excepciones.*;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.Castillo;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.modelo.unidades.Arquero;
import com.company.modelo.unidades.Espadachin;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CastilloTest {

    Mapa mapa = Mapa.getMapa();
    Jugador jugador = null;
    Jugador jugadorEnemigo = null;

    @Before
    public void resetMapa() {
        jugador = new Jugador();
        jugadorEnemigo = new Jugador();
        mapa.destruir();
        mapa = Mapa.getMapa();
    }

    @Test
    public void castilloOcupaElmapaQueDebeOcuparCuandoSeLoCreaTest() throws CasilleroLlenoException, CasilleroNoExistenteException {

        Castillo castillo = new Castillo(jugador);

        castillo.surgir(5, 5);

        assertTrue(mapa.estaOcupado(5, 5));
        assertTrue(mapa.estaOcupado(5, 6));
        assertTrue(mapa.estaOcupado(5, 7));
        assertTrue(mapa.estaOcupado(5, 8));

        assertTrue(mapa.estaOcupado(6, 5));
        assertTrue(mapa.estaOcupado(6, 6));
        assertTrue(mapa.estaOcupado(6, 7));
        assertTrue(mapa.estaOcupado(6, 8));

        assertTrue(mapa.estaOcupado(7, 5));
        assertTrue(mapa.estaOcupado(7, 6));
        assertTrue(mapa.estaOcupado(7, 7));
        assertTrue(mapa.estaOcupado(7, 8));

        assertTrue(mapa.estaOcupado(8, 5));
        assertTrue(mapa.estaOcupado(8, 6));
        assertTrue(mapa.estaOcupado(8, 7));
        assertTrue(mapa.estaOcupado(8, 8));

        assertEquals(mapa.conseguirOcupante(5, 5), castillo);
        assertEquals(mapa.conseguirOcupante(5, 6), castillo);
        assertEquals(mapa.conseguirOcupante(5, 7), castillo);
        assertEquals(mapa.conseguirOcupante(5, 8), castillo);

        assertEquals(mapa.conseguirOcupante(6, 5), castillo);
        assertEquals(mapa.conseguirOcupante(6, 6), castillo);
        assertEquals(mapa.conseguirOcupante(6, 7), castillo);
        assertEquals(mapa.conseguirOcupante(6, 8), castillo);

        assertEquals(mapa.conseguirOcupante(7, 5), castillo);
        assertEquals(mapa.conseguirOcupante(7, 6), castillo);
        assertEquals(mapa.conseguirOcupante(7, 7), castillo);
        assertEquals(mapa.conseguirOcupante(7, 8), castillo);

        assertEquals(mapa.conseguirOcupante(8, 5), castillo);
        assertEquals(mapa.conseguirOcupante(8, 6), castillo);
        assertEquals(mapa.conseguirOcupante(8, 7), castillo);
        assertEquals(mapa.conseguirOcupante(8, 8), castillo);
    }

    @Test
    public void castilloCrearMaquinaAsedioTest() throws Exception{

        Castillo castillo = new Castillo(jugador);

        castillo.surgir(5, 5);

        ArmaAsedio maquinaAsedio = new ArmaAsedio(jugador);

        castillo.crear(maquinaAsedio);

        assertTrue(jugador.estaEnPoblacion(maquinaAsedio));

    }


    @Test
    public void castilloAtacarAUnidadesEnemigasConDistanciaMenorOIgualATresTest() throws CasilleroNoExistenteException, CasilleroLlenoException, UnidadMuertaException, EnemigoInvalidoException {

        Castillo castillo = new Castillo(jugador);
        Aldeano aldeanoEnemigo = new Aldeano(jugadorEnemigo);
        Espadachin espadachinEnemigo = new Espadachin(jugadorEnemigo);
        Arquero arqueroEnemigo = new Arquero(jugadorEnemigo);

        castillo.surgir(8, 10);

        aldeanoEnemigo.establecerCoordenadasDeNacimiento(7, 10);
        mapa.ubicar(aldeanoEnemigo, 7, 10);

        espadachinEnemigo.establecerCoordenadasDeNacimiento(10, 9);
        mapa.ubicar(espadachinEnemigo, 10, 9);

        arqueroEnemigo.establecerCoordenadasDeNacimiento(13, 11);
        mapa.ubicar(arqueroEnemigo, 13, 11);


        castillo.atacar(20);

        assertEquals((Integer) 30, aldeanoEnemigo.getVida());
        assertEquals((Integer) 80, espadachinEnemigo.getVida());
        assertEquals((Integer) 55, arqueroEnemigo.getVida());

    }

    @Test
    public void borrarCastilloYVerificarQueSeBorraTest() throws CasilleroNoExistenteException, CasilleroLlenoException {

        Castillo castillo = new Castillo(jugador);

        castillo.surgir(10, 10);
        castillo.eliminar();

        assertFalse( mapa.estaOcupado(10, 10) );
        assertFalse( mapa.estaOcupado(10, 11) );
        assertFalse( mapa.estaOcupado(11, 10) );
        assertFalse( mapa.estaOcupado(11, 11) );
    }

}
