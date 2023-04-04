package com.scarlet.backscarlet.model.beans;


import com.scarlet.backscarlet.model.enums.TamanhoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SolicitarItem {

    private int produtoId;
    private int quantidade;
    private TamanhoEnum tamanho;

}
