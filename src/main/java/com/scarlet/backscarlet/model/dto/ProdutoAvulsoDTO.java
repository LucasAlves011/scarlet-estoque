package com.scarlet.backscarlet.model.dto;

import com.scarlet.backscarlet.model.Tipo;
import com.scarlet.backscarlet.model.beans.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoAvulsoDTO {
    private String nome;
    private String categoria;
    private byte[] imagem;
    private double valor;
//    private Tipo tipo;
    private int quantidade;

    public ProdutoAvulsoDTO(Produto p) {
        this.nome = p.getNome();
        this.categoria = p.getCategoria();
        this.imagem = p.getImagem();
        this.valor = p.getValor();
//        this.tipo = p.getTipo();
        this.quantidade = p.getAvulso().getQuantidade();
    }
}
