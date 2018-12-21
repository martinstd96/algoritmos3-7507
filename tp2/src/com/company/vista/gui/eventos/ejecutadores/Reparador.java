package com.company.vista.gui.eventos.ejecutadores;

import com.company.controlador.Controlador;
import com.company.excepciones.ArmaDesmontadaException;
import com.company.excepciones.ArmaMontadaException;
import com.company.excepciones.CastilloDestruidoExcecption;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioNoDisponibleException;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.terreno.Mapa;
import com.company.modelo.unidades.Aldeano;

public class Reparador implements Ejecutador {
    @Override
    public void ejecutar(Integer xOrigen, Integer yOrigen, Integer xDestino, Integer yDestino) throws Exception, ArmaDesmontadaException, ArmaMontadaException, EdificioEnConstruccionException, EdificioNoDisponibleException, CastilloDestruidoExcecption {
        Controlador controlador = Controlador.getControlador();
        Mapa mapa = Mapa.getMapa();
        Aldeano aldeano = (Aldeano) mapa.conseguirOcupante(xOrigen,yOrigen);
        Edificio edificio = (Edificio) mapa.conseguirOcupante(xDestino,yDestino);
        controlador.reparar(aldeano,edificio);
        controlador.pasarTurno();
    }
}
