package com.scarlet.backscarlet.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tipo {
    NUMERICO("NUMERICO"),NOMINAL("NOMINAL"),SEM_TAMANHO("SEM_TAMANHO"),AVULSO("AVULSO");

    private final String descricao;
}
