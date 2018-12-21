package com.company.controlador;

import com.company.DTO.Accion;
import com.company.DTO.EntidadDTO;
import com.company.DTO.JugadorDTO;
import com.company.excepciones.*;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioEnReparacionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.modelo.Jugador;
import com.company.modelo.Partida;
import com.company.modelo.Posicionable;
import com.company.modelo.edificios.Castillo;
import com.company.modelo.edificios.Cuartel;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.edificios.PlazaCentral;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.*;
import com.company.vista.gui.eventos.*;
import com.company.vista.gui.eventos.ejecutadores.*;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Controlador {

    private static Controlador instancia = new Controlador();
    private Partida partida;
    private Mapa mapa = Mapa.getMapa();
    private Logger logger = Logger.getLogger(getClass().toString());

    public static Controlador getControlador(){
        if (instancia == null){
            instancia = new Controlador();
        }
        return instancia;
    }

    public void mover(Unidad unidad, Integer posicionHorizontal, Integer posicionVertical) throws ArmaMontadaException, CasilleroNoExistenteException, MovimientoInvalidoException, CasilleroLlenoException {
        unidad.moverA(posicionHorizontal,posicionVertical);
    }

    public void reparar(Aldeano aldeano, Edificio edificio) throws EdificioLejanoException {
        aldeano.reparar(edificio);
    }

    public void construirCuartel(Aldeano aldeano, Cuartel cuartel, Integer posicionHorizontal, Integer posicionVertical) throws Exception, EdificioEnConstruccionException {
        aldeano.construir(cuartel,posicionHorizontal,posicionVertical);
    }

    public void construirPlazaCentral(Aldeano aldeano, PlazaCentral plazaCentral, Integer posicionHorizontal, Integer posicionVertical) throws Exception, EdificioEnConstruccionException {
        aldeano.construir(plazaCentral,posicionHorizontal,posicionVertical);
    }

    public void atacar(UnidadAtacante unidad, Integer posicionHorizontal, Integer posicionVertical) throws CasilleroNoExistenteException, ArmaDesmontadaException, EnemigoInvalidoException {
        Posicionable enemigo = mapa.conseguirOcupante(posicionHorizontal,posicionVertical);
        if(enemigo instanceof Unidad){
            unidad.atacarA((Unidad) enemigo);
        }
        if(enemigo instanceof Edificio) {
            unidad.atacarA((Edificio) enemigo);
        }
    }

    public void crearArmaDeAsedio(Castillo castillo, ArmaAsedio armaAsedio) throws CasilleroNoExistenteException, UnidadErroneaException, CasilleroLlenoException, MapaLlenoException {
        castillo.crear(armaAsedio);
    }

    public void crearEspadachin(Cuartel cuartel, Espadachin espadachin) throws CasilleroNoExistenteException, UnidadErroneaException, CasilleroLlenoException, MapaLlenoException, EdificioNoDisponibleException {
        cuartel.crear(espadachin);
    }

    public void crearArquero(Cuartel cuartel, Arquero arquero) throws CasilleroNoExistenteException, UnidadErroneaException, CasilleroLlenoException, MapaLlenoException, EdificioNoDisponibleException {
        cuartel.crear(arquero);
    }

    public void crearAldeano(PlazaCentral plazaCentral, Aldeano aldeano) throws CasilleroNoExistenteException, UnidadErroneaException, CasilleroLlenoException, MapaLlenoException, EdificioNoDisponibleException, EdificioEnConstruccionException, EdificioEnReparacionException {
        plazaCentral.crear(aldeano);
    }

    public void montar(ArmaAsedio armaAsedio) throws ArmaMontadaException {
        armaAsedio.montar();
    }

    public void desmontar(ArmaAsedio armaAsedio) throws ArmaMontadaException, ArmaDesmontadaException {
        armaAsedio.desmontar();
    }

    public void setPartida(Partida nuevaPartida){
        this.partida = nuevaPartida;
    }

    public void pasarTurno() throws CastilloDestruidoExcecption {
        partida.pasarTurno();
        obtenerJugadorActual().mostrarInformacion();
    }

    public JugadorDTO obtenerJugadorActual(){
        Jugador jugador = partida.obtenerJugadorCorriente();
        JugadorDTO jugadorDTO = new JugadorDTO(jugador);
        return jugadorDTO;
    }


    public EntidadDTO buscarContenidoDelCasillero(Integer posicionHorizontal, Integer posicionVertical) {
        Posicionable posicionable = null;
        try{
            posicionable = mapa.conseguirOcupante(posicionHorizontal, posicionVertical);
        }
        catch (CasilleroNoExistenteException e){
            logger.info(e.getMessage());
        }
        EntidadDTO entidad = null;
        if (posicionable != null) {
            String nombrePosicionable = posicionable.getClass().getSimpleName().substring(0, 2);
            Jugador jugador = posicionable.getJugador();
            Integer numeroJugador = jugador.getNumeroDeJugador();
            ArrayList<Accion> acciones = devolverAcciones(posicionable.getClass().getSimpleName());
            entidad = new EntidadDTO(numeroJugador, nombrePosicionable, acciones );
        }
       return entidad;
    }

    private ArrayList<Accion> devolverAcciones(String nombrePosicionable){
        ArrayList<Accion> acciones = new ArrayList<Accion>();

        if (nombrePosicionable.equals("Aldeano")){
            Accion accion1 = new Accion("mover unidad a", new Movedor());
            Accion accion2 = new Accion("reparar edificio", new Reparador());
            Accion accion3 = new Accion("construir cuartel",  new ConstructorDeCuartel());
            Accion accion4 = new Accion("construir plaza central",  new ConstructorDePlazaCentral());

            acciones.add(accion1);
            acciones.add(accion2);
            acciones.add(accion3);
            acciones.add(accion4);
        } else if (nombrePosicionable.equals("Espadachin") || nombrePosicionable.equals("Arquero")){
            Accion accion1 = new Accion("mover unidad a", new Movedor() );
            Accion accion2 = new Accion("atacar",  new Atacador());

            acciones.add(accion1);
            acciones.add(accion2);
        } else if(nombrePosicionable.equals("ArmaAsedio")){
            Accion accion1 = new Accion("mover unidad a",  new Movedor() );
            Accion accion2 = new Accion("atacar",  new Atacador() );
            Accion accion3 = new Accion("montar",  new Montador() );
            Accion accion4 = new Accion("desmontar",  new Desmontador() );

            acciones.add(accion1);
            acciones.add(accion2);
            acciones.add(accion3);
            acciones.add(accion4);
        } else if (nombrePosicionable.equals("Castillo")){
            Accion accion1 = new Accion("crear arma de asedio",  new CreadorDeArmaDeAsedio() );

            acciones.add(accion1);
        } else if (nombrePosicionable.equals("Cuartel")){
            Accion accion1 = new Accion("crear espadachin",  new CreadorDeEspadachin() );
            Accion accion2 = new Accion("crear arquero",  new CreadorDeArquero());

            acciones.add(accion1);
            acciones.add(accion2);
        } else if (nombrePosicionable.equals("PlazaCentral")){
            Accion accion1 = new Accion("crear aldeano",  new CreadorDeAldeano() );

            acciones.add(accion1);
        }

        return acciones;
    }
}
