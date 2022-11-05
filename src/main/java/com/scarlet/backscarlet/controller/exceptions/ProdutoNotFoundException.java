package com.scarlet.backscarlet.controller.exceptions;


import java.io.Serial;

public class ProdutoNotFoundException extends Exception{

    @Serial
    private static final long serialVersionUID= 1L;

    public ProdutoNotFoundException() {
        super("Produto não foi encontrado.");
    }

    public ProdutoNotFoundException(String message) {
        super(message);
    }

    public ProdutoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
