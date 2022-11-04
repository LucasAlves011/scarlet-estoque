package com.scarlet.backscarlet.model.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "nominal")
@Setter
@Getter
public class Nominal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( insertable = false, updatable = false)
    private int quantidade;

    @Column(name = "P")
    private int P;

    @Column(name = "M")
    private int M;

    @Column(name = "G")
    private int G;

    @Column(name = "GG")
    private int GG;

}
