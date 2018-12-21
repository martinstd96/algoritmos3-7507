package com.company.modelo.edificios.estados;

import com.company.excepciones.Edificio.EdificioEnReparacionException;
import com.company.excepciones.Edificio.EdificioOcupadoException;
import com.company.modelo.Posicion;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Unidad;

public class EstadoEdificioEnReparacion extends EstadoEdificio {

    public EstadoEdificioEnReparacion(Integer vidaMax, Integer vidaActual, Integer reparacion) {
        super(vidaMax,vidaActual, reparacion);
        this.vidaActual = vidaActual;
    }

    public EstadoEdificio reparar(Aldeano reparador,
                                  Integer montoDeReparacion) throws Exception {

        if (trabajadorActual == null) {
            trabajadorActual = reparador;

        } else if (trabajadorActual != reparador) {

            throw new EdificioOcupadoException("No se puede reparar este edificio, " +
                    "hay otro aldeano reparandolo!");
        }

        vidaActual += montoDeReparacion;
        return comprobarVida(this,vidaActual);
    }

    @Override
    public EstadoEdificio crear(Unidad unidad, Posicion posicion)
            throws EdificioEnReparacionException {
        throw new EdificioEnReparacionException("Edificio en reparacion, no es posible crear unidad");
    }

    @Override
    public EstadoEdificioEnConstruccion construir(Aldeano quienLoConstruye)
            throws Exception {
        throw new EdificioEnReparacionException("El edificio se esta reparando!");
    }

    @Override
    public EstadoEdificio suspender() throws Exception {
        if (this.trabajadorActual != null) {
            trabajadorActual.liberar();
        }
        this.trabajadorActual = null;
        return new EstadoEdificioInactivo(VIDA_MAXIMA, vidaActual, MONTO_REPARACION);
    }

    @Override
    public Integer getVidaActual() {
        return vidaActual;
    }

    @Override
    public EstadoEdificio ejecutarAccion() throws Exception {
        return reparar(trabajadorActual, MONTO_REPARACION);
    }
}
