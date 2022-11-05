package com.scarlet.backscarlet.model.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private byte[] imagem;

    private double valor;

    @Column(name = "categoria_nome")
    @JoinColumn(table = "categoria",name = "nome")
    private String categoria;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "tipo")
//    private Tipo tipo;

    @OneToOne(cascade = CascadeType.ALL)
    private Nominal nominal;

    @OneToOne(cascade = CascadeType.ALL)
    private Numerico numerico;

    @OneToOne(cascade = CascadeType.ALL)
    private Avulso avulso;


}
