package com.company.modelo.unidades.estados.estadosAldeano;

import com.company.excepciones.AldeanoOcupadoException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.modelo.edificios.Edificio;

public class EstadoAldeanoReparando extends EstadoAldeano {

    public EstadoAldeanoReparando(Integer vida_actual){
        super(vida_actual);
    }

    public EstadoAldeano construir(Edificio edificio) {

        throw new AldeanoOcupadoException("Estoy reparando...");
    }

    public EstadoAldeano reparar(Edificio aReparar) throws Exception, EdificioEnConstruccionException {
        if (edificioATrabajar == null) {
            edificioATrabajar = aReparar;
        } else if (edificioATrabajar != aReparar) {

            edificioATrabajar.suspender();
            edificioATrabajar = aReparar;
        }

        return this;
    }

    @Override
    public EstadoAldeano actualizar() throws Exception {
        edificioATrabajar.avanzarReparacion();

        return this;
    }
}
