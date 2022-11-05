package com.scarlet.backscarlet.model.beans;


import com.scarlet.backscarlet.model.enums.Tamanho;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SolicitarItem {

    private int idProduto;
    private int quantidade;
    private Tamanho tamanho;

}
