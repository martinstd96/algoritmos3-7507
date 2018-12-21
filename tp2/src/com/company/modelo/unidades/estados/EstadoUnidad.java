package com.company.modelo.unidades.estados;

import com.company.excepciones.Edificio.EdificioEnConstruccionException;
import com.company.excepciones.UnidadMuertaException;

public abstract class EstadoUnidad {

	protected final Integer VIDA_MAXIMA;
	protected Integer vidaActual;
	protected final Integer COSTO;

	public EstadoUnidad(Integer vidaMaxima,Integer vida_actual,Integer costo) {

		this.VIDA_MAXIMA = vidaMaxima;
		vidaActual = vida_actual;
		COSTO = costo;
	}

	public EstadoUnidad recibirDanio(Integer montoDeDanio)
			throws Exception, EdificioEnConstruccionException {

		if(montoDeDanio < 0){
			throw new RuntimeException("El daño recibido fue negativo todo mal.");
		}

		this.vidaActual -= montoDeDanio;

		if(this.vidaActual <= 0) {
			vidaActual = 0;
			throw new UnidadMuertaException("Esta unidad se ha muerto");
		}

		return this;
	}

	public Integer getVidaActual() {
		return this.vidaActual;

	}

	public abstract EstadoUnidad actualizar() throws Exception;

	public Integer getCosto() {
		return COSTO;
	}
}
