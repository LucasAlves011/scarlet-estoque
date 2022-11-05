package com.scarlet.backscarlet.model.dto;

import com.scarlet.backscarlet.model.beans.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoNominalDTO {

    private String nome;
    private String categoria;
    private byte[] imagem;
    private double valor;
//    private Tipo tipo;
    private int quantidade;
    private int P;
    private int M;
    private int G;
    private int GG;

    public ProdutoNominalDTO(Produto p) {
        this.nome = p.getNome();
        this.categoria = p.getCategoria();
        this.imagem = p.getImagem();
        this.valor = p.getValor();
//        this.tipo = p.getTipo();
        this.quantidade = p.getNominal().getQuantidade();
        this.P = p.getNominal().getP();
        this.M = p.getNominal().getM();
        this.G = p.getNominal().getG();
        this.GG = p.getNominal().getGG();
    }

}
