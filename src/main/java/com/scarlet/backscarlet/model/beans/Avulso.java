package com.scarlet.backscarlet.model.beans;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "avulso")
@NoArgsConstructor
public class Avulso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column
    private int quantidade;

    public Avulso(int quantidade) {
        this.quantidade = quantidade;
    }
}
