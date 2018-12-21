package com.company.modelo.unidades;

import com.company.excepciones.*;

import com.company.modelo.Ataque;
import com.company.modelo.Jugador;
import com.company.modelo.Posicionable;
import com.company.modelo.edificios.Edificio;
import com.company.modelo.unidades.estados.estadosArmaAsedio.EstadoArmaAsedio;
import com.company.modelo.unidades.estados.estadosArmaAsedio.EstadoArmaAsedioDesmontada;
import com.company.modelo.unidades.estados.estadosArmaAsedio.EstadoArmaAsedioMontada;

public class ArmaAsedio extends UnidadAtacante {

	private EstadoArmaAsedio estado;
	private Integer VIDA_MAXIMA = 150;

	public ArmaAsedio(Jugador jugador) {
		
		super(jugador);
		this.estado =  new EstadoArmaAsedioDesmontada(this,VIDA_MAXIMA);
		rangoAtaque = 5;
		danioAEdificio = 75;
		danioAUnidad = 0;

	}

	@Override
	public Integer getVidaMaxima() {
		return VIDA_MAXIMA;
	}

	@Override
	public void atacarA(Unidad enemigo) throws EnemigoInvalidoException {
			throw new EnemigoInvalidoException("El arma asedio no puede atacar unidades");
	}

	@Override
	public void atacarA(Edificio enemigo) throws ArmaDesmontadaException, EnemigoInvalidoException {
		estado.atacarA(enemigo,new Ataque(rangoAtaque,jugador,posicion),danioAEdificio);
	}

	@Override
	public void ubicar(Integer posicionHorizontal, Integer posicionVertical) {

	}

	@Override
	public void actualizar() {
		//no se actualiza
	}

	//toma 1 turno montar/desmontar, si esta montada puede atacar pero no moverse.
	
	public void montar() throws ArmaMontadaException {
		this.estado = estado.montar();
	}
	
	public void desmontar() throws ArmaDesmontadaException {
		this.estado = estado.desmontar();
	}

	@Override
	public void moverA(Integer posicionHorizontal, Integer posicionVertical)
			throws CasilleroLlenoException, CasilleroNoExistenteException, ArmaMontadaException, MovimientoInvalidoException {
		estado.moverA(posicion, posicionHorizontal,posicionVertical);
	}

}
