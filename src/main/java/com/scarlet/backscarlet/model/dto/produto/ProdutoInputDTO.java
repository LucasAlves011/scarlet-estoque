package com.scarlet.backscarlet.model.dto.produto;

import com.scarlet.backscarlet.model.beans.Avulso;
import com.scarlet.backscarlet.model.beans.Nominal;
import com.scarlet.backscarlet.model.beans.Numerico;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProdutoInputDTO {

    private String nome;

    private byte[] imagem;

    private double valor;

    private List<String> categorias;

    private Nominal nominal;

    private Numerico numerico;

    private Avulso avulso;

}
