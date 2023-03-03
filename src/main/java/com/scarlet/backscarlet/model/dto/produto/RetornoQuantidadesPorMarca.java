package com.scarlet.backscarlet.model.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RetornoQuantidadesPorMarca {

    private String marca;
    private long produtos;
    private int unidades;

}
