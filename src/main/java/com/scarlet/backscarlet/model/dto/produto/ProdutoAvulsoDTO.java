package com.scarlet.backscarlet.model.dto.produto;

import com.scarlet.backscarlet.model.beans.Categoria;
import com.scarlet.backscarlet.model.beans.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
public class ProdutoAvulsoDTO extends ProdutoDTO{
    public ProdutoAvulsoDTO(Produto p)  {
        super(p.getId(),p.getNome(),p.getCategorias().stream().map(Categoria::getNome).collect(Collectors.toList()),
                p.getImagem(),p.getValor(),p.getAvulso().getQuantidade(),p.getMarca(),"avulso");
    }
}
