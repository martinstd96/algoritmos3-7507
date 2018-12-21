package com.company.modelo.edificios.estados;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioReparadoException;
import com.company.excepciones.MapaLlenoException;
import com.company.modelo.Posicion;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Unidad;

public class EstadoEdificioCreando extends EstadoEdificio {

    EstadoEdificioCreando(Integer vida_maxima,Integer vidaActual, Integer monto_reparacion) {

        super(vida_maxima,vidaActual, monto_reparacion);
        this.vidaActual = vidaActual;
    }

    @Override
    public EstadoEdificio crear(Unidad unidad, Posicion posicion)
            throws CasilleroLlenoException, CasilleroNoExistenteException, MapaLlenoException {
        posicion.colocarEnCasilleroLibreMasCercano(unidad);
        return this;
    }

    @Override
    public EstadoEdificio reparar(Aldeano reparador, Integer montoDeReparacion) {
        throw new RuntimeException("El edificio esta creando!");
    }

    @Override
    public EstadoEdificio construir(Aldeano quienLoConstruye)  {
       throw new RuntimeException("El edificio esta creando!");
    }

    @Override
    public EstadoEdificio suspender() {

       return new EstadoEdificioInactivo(VIDA_MAXIMA,vidaActual, MONTO_REPARACION);
    }

    @Override
    public Integer getVidaActual()  {
       return this.vidaActual;
    }

    @Override
    public EstadoEdificio ejecutarAccion() {
        return this;
    }
}
