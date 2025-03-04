package com.impacta.api.livraria.exceptions;

public class LivroAllReadyExistsException extends RuntimeException {

    public LivroAllReadyExistsException(String message) {
        super(message);
    }

    public LivroAllReadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
