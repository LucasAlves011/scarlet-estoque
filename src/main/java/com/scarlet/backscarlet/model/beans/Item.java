package com.scarlet.backscarlet.model.beans;

import com.scarlet.backscarlet.model.enums.Tamanho;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Produto produto;

    private int unidades;

    @Enumerated(EnumType.STRING)
    @JoinColumn(table = "tamanho")
    private Tamanho tamanho;

}
