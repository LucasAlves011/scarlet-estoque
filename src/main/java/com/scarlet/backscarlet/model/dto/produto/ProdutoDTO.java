package com.scarlet.backscarlet.model.dto.produto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public abstract class ProdutoDTO {

    private int id;
    private String nome;
    private List<String> categorias;
    private byte[] imagem;
    private double valor;
    //    private Tipo tipo;
    private int quantidade;

    public ProdutoDTO(int id,String nome, List<String> categorias, byte[] imagem, double valor, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.categorias = categorias;
        this.imagem = imagem;
        this.valor = valor;
        this.quantidade = quantidade;
    }
}
