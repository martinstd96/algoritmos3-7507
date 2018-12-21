package com.company.modelo.unidades.estados.estadosArmaAsedio;

import com.company.excepciones.*;
import com.company.modelo.Ataque;
import com.company.modelo.Posicion;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.estados.EstadoUnidad;
import com.company.modelo.unidades.ArmaAsedio;

public abstract class EstadoArmaAsedio extends EstadoUnidad {

    ArmaAsedio maquinaAsedio;
    protected static Integer VIDA_MAXIMA = 150;
    protected static Integer COSTO = 200;


    public EstadoArmaAsedio(ArmaAsedio maquinaAsedio,Integer vidaActual) {
        super(VIDA_MAXIMA,vidaActual,COSTO);
        this.maquinaAsedio = maquinaAsedio;
    }

    public abstract EstadoArmaAsedioDesmontada desmontar() throws ArmaDesmontadaException;

    public abstract EstadoArmaAsedio montar() throws ArmaMontadaException;

    public abstract void moverA(Posicion posicion, Integer posicionHorizontal, Integer posicionVertical)
            throws CasilleroNoExistenteException, CasilleroLlenoException, ArmaMontadaException,
            MovimientoInvalidoException;

    public abstract void atacarA(Edificio enemigo, Ataque ataque, Integer danio)
            throws ArmaDesmontadaException, EnemigoInvalidoException;
}
