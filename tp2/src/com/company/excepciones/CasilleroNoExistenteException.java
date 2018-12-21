package com.company.excepciones;

@SuppressWarnings("serial")
public class CasilleroNoExistenteException extends Exception {
	public CasilleroNoExistenteException(String mensaje) {
		super(mensaje);
	}
}
