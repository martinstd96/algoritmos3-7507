package com.company.excepciones;

public class EdificioInexistenteEnConstruccionesException extends RuntimeException {
    public EdificioInexistenteEnConstruccionesException(String mensaje) {
        super(mensaje);
    }
}
