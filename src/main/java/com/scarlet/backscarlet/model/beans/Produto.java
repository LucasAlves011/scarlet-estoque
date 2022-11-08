package com.scarlet.backscarlet.model.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "produto_categorias",joinColumns = @JoinColumn(name = "produto_id")
            ,inverseJoinColumns = @JoinColumn(name = "categoria_nome"))
    private List<Categoria> categorias;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "tipo")
//    private Tipo tipo;

    @OneToOne(cascade = CascadeType.ALL)
    private Nominal nominal;

    @OneToOne(cascade = CascadeType.ALL)
    private Numerico numerico;

    @OneToOne(cascade = CascadeType.ALL)
    private Avulso avulso;

    public void adicionarUmaCategoria(Categoria c){
        categorias.add(c);
    }

    public void removerUmaCategoria(Categoria c) { categorias.remove(c);}

}
