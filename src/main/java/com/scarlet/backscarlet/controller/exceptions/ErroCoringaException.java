package com.scarlet.backscarlet.controller.exceptions;

import java.io.Serial;

public class ErroCoringaException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ErroCoringaException(String message) {
        super(message);
    }

    public ErroCoringaException(String message, Throwable cause) {
        super(message, cause);
    }

}
