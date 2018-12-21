package com.company.excepciones;

@SuppressWarnings("serial")
public class FueraDeMapaException extends Exception{
	public FueraDeMapaException(String mensaje) {
		super(mensaje);
	}
}
