package com.scarlet.backscarlet.model.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class DTOteste {

    private String nome;

    private String marca;

    private double valor;

    private List<String> categorias;

//    private Nominal nominal;
//
//    private Numerico numerico;
//
//    private Avulso avulso;

}
