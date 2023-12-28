package com.scarlet.backscarlet.model.enums;

import lombok.Getter;

@Getter
public enum TamanhoEnum {

    P("P"), M("M"), G("G"), GG("GG"), T36("36"), T38("38"), T40("40"), T42("42"), T44("44"), T46("46"), T48("48"), T50("50"), AVULSO("Avulso");

    private final String tamanho;

    TamanhoEnum(String tamanho) {
        this.tamanho = tamanho;
    }

}
