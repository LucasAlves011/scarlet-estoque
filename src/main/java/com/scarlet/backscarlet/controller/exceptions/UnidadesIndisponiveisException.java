package com.scarlet.backscarlet.controller.exceptions;

import java.io.Serial;

public class UnidadesIndisponiveisException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnidadesIndisponiveisException(String message) {
        super(message);
    }

    public UnidadesIndisponiveisException(String message, Throwable cause) {
        super(message, cause);
    }

}
