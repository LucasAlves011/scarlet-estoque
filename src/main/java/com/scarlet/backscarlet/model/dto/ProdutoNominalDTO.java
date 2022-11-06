package com.scarlet.backscarlet.model.dto;

import com.scarlet.backscarlet.model.beans.Categoria;
import com.scarlet.backscarlet.model.beans.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public class ProdutoNominalDTO extends ProdutoDTO{

    private int P;
    private int M;
    private int G;
    private int GG;

    public ProdutoNominalDTO(Produto p) {
        super(p.getNome(),p.getCategorias().stream().map(Categoria::getNome).collect(Collectors.toList()),
                p.getImagem(),p.getValor(),p.getNominal().getQuantidade());
        this.P = p.getNominal().getP();
        this.M = p.getNominal().getM();
        this.G = p.getNominal().getG();
        this.GG = p.getNominal().getGG();
    }

}
