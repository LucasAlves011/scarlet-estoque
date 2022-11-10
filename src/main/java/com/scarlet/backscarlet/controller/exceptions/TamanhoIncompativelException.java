package com.scarlet.backscarlet.controller.exceptions;

import java.io.Serial;

public class TamanhoIncompativelException extends RuntimeException{

    @Serial
    private static final long serialVersionUID= 1L;

    public TamanhoIncompativelException(String message) {
        super(message);
    }

    public TamanhoIncompativelException(String message, Throwable cause) {
        super(message, cause);
    }

}
