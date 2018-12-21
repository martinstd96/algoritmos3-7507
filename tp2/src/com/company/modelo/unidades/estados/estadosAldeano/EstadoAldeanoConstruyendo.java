package com.company.modelo.unidades.estados.estadosAldeano;

import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.modelo.edificios.Edificio;


public class EstadoAldeanoConstruyendo extends EstadoAldeano {

    public EstadoAldeanoConstruyendo(Integer vidaActual) {
        super(vidaActual);
    }

    @Override
    public EstadoAldeano reparar(Edificio aReparar) throws EdificioEnConstruccionException {
        throw new EdificioEnConstruccionException("El edificio esta siendo construido");
    }

    @Override
    public EstadoAldeano construir(Edificio edificio)
            throws Exception, EdificioEnConstruccionException {
        if (edificioATrabajar == null) {
            edificioATrabajar = edificio;
        } else if (edificioATrabajar != edificio) {
            edificioATrabajar.suspender();
            edificioATrabajar = edificio;
        }

        return this;
    }

    @Override
    public EstadoAldeano actualizar() throws Exception {

        edificioATrabajar.avanzarConstruccion();

        return this;
    }
}
