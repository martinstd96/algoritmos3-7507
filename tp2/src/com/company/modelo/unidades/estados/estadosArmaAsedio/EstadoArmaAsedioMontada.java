package com.company.modelo.unidades.estados.estadosArmaAsedio;

import com.company.excepciones.*;
import com.company.excepciones.ArmaMontadaException;
import com.company.modelo.Ataque;
import com.company.modelo.Posicion;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.modelo.unidades.estados.EstadoUnidad;

public class EstadoArmaAsedioMontada extends EstadoArmaAsedio {

    public EstadoArmaAsedioMontada(ArmaAsedio armaAsedio,Integer vidaActual) {
        super(armaAsedio,vidaActual);
    }

    public EstadoArmaAsedioDesmontada desmontar() {
        return new EstadoArmaAsedioDesmontada(maquinaAsedio,vidaActual);
    }

    public EstadoArmaAsedioMontada montar() throws ArmaMontadaException {
        throw new ArmaMontadaException("la maquina de asedio ya esta montada");
    }

    @Override
    public void moverA(Posicion posicion, Integer posicionHorizontal, Integer posicionVertical)
            throws ArmaMontadaException {
        throw new ArmaMontadaException("la maquina de asedio no se puede mover porque esta montada");
    }

    public void atacarA(Edificio enemigo, Ataque ataque, Integer danio)
            throws EnemigoInvalidoException {

        ataque.atacar(enemigo,danio);
    }

    @Override
    public EstadoUnidad actualizar() {
        return this;
    }
}