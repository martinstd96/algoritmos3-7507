package edificios;

import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioEnReparacionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.PlazaCentral;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlazaCentralTest {

    private Mapa mapa = Mapa.getMapa();
    private Jugador jugador;
    private Aldeano peon;
    PlazaCentral central;

    @Before
    public void resetMapa() {
        mapa.destruir();
        mapa = Mapa.getMapa();
        jugador = new Jugador();
        peon = new Aldeano(jugador);
        central = new PlazaCentral(jugador);
    }

    @Test
    public void plazaCentralCrearAldeanoTestYVerificarExistencia()
            throws CasilleroNoExistenteException,
            CasilleroLlenoException {

        mapa.ubicar(peon, 16, 21);
        peon.establecerCoordenadasDeNacimiento(16, 21);

        try {
            peon.construir(central, 16, 22);
        } catch (Exception | EdificioEnConstruccionException ignored) {
        }

        /*   21  22  23
         *
         * 16  A  pc  pc
         *
         * 17     pc  pc
         *
         * */

        try {
            central.crear(new Aldeano(jugador));
        } catch (MapaLlenoException | EdificioNoDisponibleException | UnidadErroneaException ignored) {
        }

        assertTrue(mapa.estaOcupado(16, 21));
        assertTrue(mapa.estaOcupado(16, 23));
        assertTrue(mapa.estaOcupado(17, 22));
    }

    @Test
    public void recibirDanioYVerificarDanioTest() throws Exception,
            EdificioEnConstruccionException, EdificioDestruidoExcepcion {

        peon.establecerCoordenadasDeNacimiento(5, 3);

        peon.construir(central, 5, 4);

        central.avanzarConstruccion();
        central.avanzarConstruccion();
        central.avanzarConstruccion();

        central.recibirDanio(15);

        assertEquals(central.getVida(), (Integer) 435);
    }

    @Test
    public void verificarReparacionEnTiempoIndicadoTest()
            throws Exception, EdificioEnConstruccionException,
            EdificioDestruidoExcepcion {

        peon.establecerCoordenadasDeNacimiento(5, 3);

        try {
            peon.construir(central, 5, 4);
        } catch (DistanciaInvalidaException ignored) {
        }

        central.avanzarConstruccion();
        central.avanzarConstruccion();
        central.avanzarConstruccion();

        central.recibirDanio(50);

        peon.reparar(central);

        central.avanzarReparacion();

        assertEquals(central.getVida(), (Integer) 450);

    }

    @Test
    public void verificarQueNoCreaAldeanosCuandoEstaEnReparacionTest()
            throws Exception, EdificioEnConstruccionException,
            EdificioDestruidoExcepcion {

        peon.establecerCoordenadasDeNacimiento(5, 5);

        peon.construir(central, 5, 6);

        central.avanzarConstruccion();
        central.avanzarConstruccion();
        central.avanzarConstruccion();

        central.recibirDanio(50);

        central.reparar(peon);

        try {
            central.crear(new Aldeano(jugador));
        } catch (EdificioNoDisponibleException ignored) {
        }

    }

	@Test
	public void crearPlazaCentralYVerificarQueSePosicionaEnPosicionIndicadaTest()
            throws Exception {

		PlazaCentral plazaCentral = new PlazaCentral(jugador);

		plazaCentral.construir(peon,3,4);

		plazaCentral.avanzarConstruccion();
		plazaCentral.avanzarConstruccion();
		plazaCentral.avanzarConstruccion();

        assertTrue(mapa.estaOcupado(3,4));

	}

	@Test
    public void borrarPlazaCentralYVerificarQueSeBorraTest() throws CasilleroNoExistenteException, CasilleroLlenoException {

        PlazaCentral plazaCentral = new PlazaCentral(jugador);

        plazaCentral.surgir(0, 0);
        plazaCentral.eliminar();

        assertFalse( mapa.estaOcupado(0, 0) );
        assertFalse( mapa.estaOcupado(0, 1) );
        assertFalse( mapa.estaOcupado(1, 0) );
        assertFalse( mapa.estaOcupado(1, 1) );

    }

}
