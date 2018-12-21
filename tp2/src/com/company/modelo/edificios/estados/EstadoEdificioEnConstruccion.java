package com.company.modelo.edificios.estados;

import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.Edificio.EdificioOcupadoException;
import com.company.excepciones.Edificio.EdificioReparadoException;
import com.company.modelo.Posicion;
import com.company.modelo.unidades.Aldeano;
import com.company.modelo.unidades.Unidad;

public class EstadoEdificioEnConstruccion extends EstadoEdificio {


    public EstadoEdificioEnConstruccion(Integer vidaMax,Integer vidaActual, Integer reparacion) {
        super(vidaMax, vidaActual,reparacion);
    }

    @Override
    public EstadoEdificio crear(Unidad unidad, Posicion posicion)
            throws EdificioEnConstruccionException {
        throw new EdificioEnConstruccionException("No es posible crear unidades, edificio construyendose");
    }

    @Override
    public EstadoEdificio recibirDanio(Integer montoDeDanio)
            throws EdificioEnConstruccionException {
        throw new EdificioEnConstruccionException("No es posible atacar este edificio! " +
                "en construccion");
    }

    @Override
    public Integer getVidaActual() throws Exception {
        throw new Exception("Este edificio aun no existe");
    }

    @Override
    public EstadoEdificio ejecutarAccion() throws Exception {

       return construir(trabajadorActual);

    }

    @Override
    public EstadoEdificio reparar(Aldeano reparador,
                                  Integer montoDeReparacion)
            throws EdificioEnConstruccionException {

        throw new EdificioEnConstruccionException("imposible reparar edificio en construccion");
    }

    @Override
    public EstadoEdificio construir(Aldeano quienLoConstruye) throws Exception {

        if (trabajadorActual == null) {

            trabajadorActual = quienLoConstruye;

        } else if (trabajadorActual != quienLoConstruye) {

            throw new EdificioOcupadoException("No se puede construir este edificio," +
                    " hay otro aldeano construyendolo!");
        }

        vidaActual += VIDA_MAXIMA / 3;
        return comprobarVida(this,vidaActual);

    }

    @Override
    public EstadoEdificio suspender() throws Exception {
        if (trabajadorActual != null) {
            trabajadorActual.liberar();
        }
        trabajadorActual = null;
        return new EstadoEdificioInactivo(VIDA_MAXIMA, vidaActual, MONTO_REPARACION);
    }
}
