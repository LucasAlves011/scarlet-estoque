package com.scarlet.backscarlet.model.beans;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity(name = "nominal")
@Setter
@Getter
@ToString
public class Nominal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(insertable = false, updatable = false)
    private int quantidade;

    @Column(name = "P")
    private int P;

    @Column(name = "M")
    private int M;

    @Column(name = "G")
    private int G;

    @Column(name = "GG")
    private int GG;

    public Nominal(int p, int m, int g, int gg) {
        P = p;
        M = m;
        G = g;
        GG = gg;
    }

    public Nominal() {

    }
}
