package com.scarlet.backscarlet.model.beans;

import com.scarlet.backscarlet.model.enums.TamanhoEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
//    @Setter(AccessLevel.NONE)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    private Produto produto;

    private int unidades;

//    @Enumerated(EnumType.STRING)
//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "tamanho")
    private Tamanho tamanho;

    public Item(Produto produto, int unidades, Tamanho tamanho) {
        this.produto = produto;
        this.unidades = unidades;
        this.tamanho = tamanho;
    }

    public Item() {
    }

    public void retirarDoEstoque(){
        produto.retirarEstoque(tamanho,unidades);
    }

}
