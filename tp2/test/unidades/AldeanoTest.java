package unidades;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.DistanciaInvalidaException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.EdificioDestruidoExcepcion;
import com.company.modelo.Jugador;
import com.company.modelo.edificios.Castillo;
import com.company.modelo.edificios.Cuartel;
import com.company.modelo.edificios.PlazaCentral;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AldeanoTest {

    private Mapa mapa = Mapa.getMapa();
    private Jugador jugador = null;
    Aldeano aldeano = null;

    @Before
    public void resetMapa()  {
        mapa.destruir();
        mapa = Mapa.getMapa();
        jugador = new Jugador();
        aldeano = new Aldeano(jugador);
    }

    @Test
    public void recibirDanio(){
        try {
            aldeano.recibirDanio(50);
        } catch (Exception ignored) {}
        assertEquals(aldeano.getVida(),(Integer)0);
    }

    @Test
    public void verificarQueSumaOroSiNoEstaOcupadoTest() throws Exception {

        aldeano.actualizar();
        assertEquals(jugador.getOro(), (Integer) 120);
    }

    @Test
    public void verificarQueSumaOroUnaVezPorTurnoTest() throws Exception {

        Aldeano aldeano = new Aldeano(jugador);

        aldeano.actualizar();

        //Actualizo el turno 4 veces e incrementa jugador su oro 4 veces
        aldeano.actualizar();

        aldeano.actualizar();

        aldeano.actualizar();

        assertEquals(jugador.getOro(), (Integer) 180);

    }

    @Test
    public void verificarQueMientrasReparaNoSumaOroTest()
            throws Exception, EdificioEnConstruccionException,
            EdificioDestruidoExcepcion {

        Aldeano aldeano = new Aldeano(jugador);

        PlazaCentral plaza = new PlazaCentral(jugador);
        aldeano.establecerCoordenadasDeNacimiento(5, 4);

        aldeano.ubicar(5,4);
        aldeano.construir(plaza, 5, 5);
        //3 turnos en construirse

        aldeano.actualizar();

        aldeano.actualizar();

        aldeano.actualizar();

        //aca dejo de construir suma oro
        plaza.recibirDanio(30);

        aldeano.actualizar();

        //no suma oro por estar reparando la plaza
        assertEquals(jugador.getOro(), (Integer) 20);

    }

    @Test
    public void verificarQueMientrasConstruyeNoSumaOroTest() throws Exception, EdificioEnConstruccionException {

        Aldeano aldeano = new Aldeano(jugador);
        PlazaCentral plaza = new PlazaCentral(jugador);
        aldeano.establecerCoordenadasDeNacimiento(8, 6);

        try {
            aldeano.construir(plaza, 8, 7);
        } catch (Exception ignored) { }


        aldeano.actualizar();

        aldeano.actualizar();

        aldeano.actualizar();

        //Se libera y suma oro de nuevo
        assertEquals(jugador.getOro(), (Integer) 20);

    }

    @Test
    public void verificarQueAldeanoAunHeridoSumaOroTest() throws Exception {

        Aldeano aldeano = new Aldeano(jugador);

        aldeano.recibirDanio(20);

        aldeano.actualizar();

        assertEquals(jugador.getOro(), (Integer) 120);

    }

    @Test
    public void construirCuartelYVerificarConstruccionTest()
            throws CasilleroNoExistenteException, CasilleroLlenoException {

        Aldeano aldeano = new Aldeano(jugador);

        aldeano.establecerCoordenadasDeNacimiento(5, 5);

        mapa.ubicar(aldeano, 5, 5);

        Cuartel cuartel = new Cuartel(jugador);

        assertFalse(mapa.estaOcupado(5, 6));

        try {
            aldeano.construir(cuartel, 5, 5);

        } catch (Exception | EdificioEnConstruccionException ignored) { }

        assertTrue(mapa.estaOcupado(5, 5));
    }

    @Test
    public void construirPlazaCentralTestYVerificarConstruccionTest() {

        Aldeano aldeano = new Aldeano(jugador);

        aldeano.establecerCoordenadasDeNacimiento(5, 5);

        PlazaCentral plazaCentral = new PlazaCentral(jugador);


        assertFalse(mapa.estaOcupado(5, 6));

        try {
            aldeano.construir(plazaCentral, 5, 6);

        } catch (Exception | EdificioEnConstruccionException ignored) {

        }
        assertTrue(mapa.estaOcupado(5, 6));

    }

    @Test
    public void repararCastilloTest() throws Exception, EdificioEnConstruccionException {
        Castillo castillo = new Castillo(jugador);
        Aldeano aldeano = new Aldeano(jugador);

        aldeano.establecerCoordenadasDeNacimiento(5, 5);

        //el castillo no se construye...
        castillo.surgir(5, 6);

        castillo.recibirDanio(15);

        try {
            aldeano.reparar(castillo);
        } catch (Exception ignored) { }

        assertEquals((Integer) 1000, castillo.getVida());
    }

    @Test
    public void repararPlazaCentralTest()
            throws Exception, EdificioEnConstruccionException{

        Aldeano aldeano = new Aldeano(jugador);

        PlazaCentral plazaCentral = new PlazaCentral(jugador);

        aldeano.establecerCoordenadasDeNacimiento(5, 5);

        try {
            aldeano.construir(plazaCentral, 5, 5);
        } catch (DistanciaInvalidaException e) {
        }

        aldeano.actualizar();
        aldeano.actualizar();
        aldeano.actualizar();

        plazaCentral.recibirDanio(60);

        aldeano.reparar(plazaCentral);

        assertEquals((Integer) 415, plazaCentral.getVida());
    }

    @Test
    public void repararCuartelTest() throws Exception, EdificioEnConstruccionException {

        Aldeano aldeano = new Aldeano(jugador);

        Cuartel cuartel = new Cuartel(jugador);

        aldeano.establecerCoordenadasDeNacimiento(5, 5);

        try {
            aldeano.construir(cuartel, 5, 6);
        } catch (DistanciaInvalidaException ignored) {
        }

        aldeano.actualizar();

        aldeano.actualizar();

        aldeano.actualizar();

        cuartel.recibirDanio(60);

        aldeano.reparar(cuartel);

        aldeano.actualizar();

        assertEquals((Integer) 250, cuartel.getVida());

    }

}

