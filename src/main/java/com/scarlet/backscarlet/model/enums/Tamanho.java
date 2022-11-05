package com.scarlet.backscarlet.model.enums;

import lombok.Getter;

@Getter
public enum Tamanho {

    P("P"),M("M"),G("G"),GG("GG"),T36("36"),T38("38"),T40("40"),T42("42"),T44("44"),T46("46"),T48("48"),T50("50");

    private final String tamanho;

    Tamanho(String tamanho) {
        this.tamanho = tamanho;
    }

}
