package com.company.modelo.unidades.estados.estadosAldeano;

import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioTerminadoException;
import com.company.excepciones.UnidadMuertaException;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.estados.EstadoUnidad;

public abstract class EstadoAldeano extends EstadoUnidad {

    protected final Integer PRODUCCION_ORO = 20;
    protected static Integer vidaMAXIMA = 50;
    protected static Integer COSTO = 25;

    protected Edificio edificioATrabajar;

    public EstadoAldeano(Integer vida_actual) {
        super(vidaMAXIMA,vida_actual,COSTO);
    }

    public void abandonarTareaActual() throws Exception, EdificioEnConstruccionException {
        if (edificioATrabajar == null)
            throw new EdificioTerminadoException("No tengo una tarea actual.");

        edificioATrabajar.suspender();
    }

    public EstadoAldeano recibirDanio(Integer montoDeDanio) throws Exception, EdificioEnConstruccionException {
        this.vidaActual -= montoDeDanio;

        if (this.vidaActual <= 0) {
            vidaActual = 0;
            edificioATrabajar.suspender();

            edificioATrabajar = null;
            throw new UnidadMuertaException("El aldeano murio");

        }

        return this;
    }

    public abstract EstadoAldeano construir(Edificio edificio) throws Exception, EdificioEnConstruccionException;

    public abstract EstadoAldeano reparar(Edificio edificio) throws Exception, EdificioEnConstruccionException;

    public abstract EstadoAldeano actualizar() throws Exception;

}
