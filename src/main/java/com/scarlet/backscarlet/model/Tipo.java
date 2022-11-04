package com.scarlet.backscarlet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tipo {
    NUMERICO("NUMERICO"),NOMINAL("NOMINAL"),SEM_TAMANHO("SEM_TAMANHO");

    private final String descricao;
}
