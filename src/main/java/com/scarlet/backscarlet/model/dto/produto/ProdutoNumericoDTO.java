package com.scarlet.backscarlet.model.dto.produto;

import com.scarlet.backscarlet.model.beans.Categoria;
import com.scarlet.backscarlet.model.beans.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public class ProdutoNumericoDTO extends ProdutoDTO{
    private int t36;
    private int t38;
    private int t40;
    private int t42;
    private int t44;
    private int t46;
    private int t48;
    private int t50;

    public ProdutoNumericoDTO(Produto p) {
        super(p.getId(),p.getNome(),p.getCategorias().stream().map(Categoria::getNome).collect(Collectors.toList()),
                p.getImagem(),p.getValor(),p.getNumerico().getQuantidade());
        this.t36 = p.getNumerico().getT36();
        this.t38 = p.getNumerico().getT38();
        this.t40 = p.getNumerico().getT40();
        this.t42 = p.getNumerico().getT42();
        this.t44 = p.getNumerico().getT44();
        this.t46 = p.getNumerico().getT46();
        this.t48 = p.getNumerico().getT48();
        this.t50 = p.getNumerico().getT50();
    }
}
