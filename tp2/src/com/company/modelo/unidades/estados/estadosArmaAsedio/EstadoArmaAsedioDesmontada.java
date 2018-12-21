package com.company.modelo.unidades.estados.estadosArmaAsedio;

import com.company.excepciones.*;

import com.company.modelo.Ataque;
import com.company.modelo.Posicion;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.ArmaAsedio;
import com.company.modelo.unidades.estados.EstadoUnidad;

public class EstadoArmaAsedioDesmontada extends EstadoArmaAsedio {
    ArmaAsedio maquinaAsedio;

    public EstadoArmaAsedioDesmontada(ArmaAsedio armaAsedio, Integer vidaActual) {
        super(armaAsedio,vidaActual);
        maquinaAsedio = armaAsedio;

    }

    public EstadoArmaAsedioDesmontada desmontar() throws ArmaDesmontadaException {
        throw new ArmaDesmontadaException("la maquina de asedio ya esta desmontada");
    }

    public EstadoArmaAsedio montar() {

        return new EstadoArmaAsedioMontada(maquinaAsedio,vidaActual);
    }

    public void moverA(Posicion posicion, Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException, MovimientoInvalidoException {

        posicion.moverA(posicionHorizontal, posicionVertical);

    }

    public void atacarA(Edificio enemigo, Ataque ataque,Integer danio) throws ArmaDesmontadaException {
        throw new ArmaDesmontadaException("la maquina de asedio no puede atacar desmontada");
    }

    @Override
    public EstadoUnidad actualizar() {
        return this;
    }
}