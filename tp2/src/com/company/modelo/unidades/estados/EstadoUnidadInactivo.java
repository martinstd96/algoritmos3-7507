package com.company.modelo.unidades.estados;

public class EstadoUnidadInactivo extends EstadoUnidad {

    public EstadoUnidadInactivo(Integer vidaMaxima,Integer vida_actual,Integer costo) {

        super(vidaMaxima,vida_actual,costo);
    }

    @Override
    public EstadoUnidad actualizar()  {
        return null;
    }

}
