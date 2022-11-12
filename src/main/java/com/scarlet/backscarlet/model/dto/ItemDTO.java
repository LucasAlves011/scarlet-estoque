package com.scarlet.backscarlet.model.dto;


import com.scarlet.backscarlet.model.beans.Item;
import com.scarlet.backscarlet.model.beans.Tamanho;
import lombok.Data;

@Data
public class ItemDTO {

    private String nomeProduto;
    private int unidades;
    private Tamanho tamanho;
    private double subTotal;

    public ItemDTO(Item item) {
        this.nomeProduto = item.getProduto().getNome();
        this.unidades = item.getUnidades();
        this.tamanho = item.getTamanho();
        this.subTotal = item.getUnidades() * item.getProduto().getValor();
    }
}
