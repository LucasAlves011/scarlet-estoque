package com.scarlet.backscarlet.controller.exceptions;


import java.io.Serial;

public class PersonNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID= 1L;

    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
