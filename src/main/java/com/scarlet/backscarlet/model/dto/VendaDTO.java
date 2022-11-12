package com.scarlet.backscarlet.model.dto;

import com.scarlet.backscarlet.model.beans.Venda;
import com.scarlet.backscarlet.model.enums.TipoPagamento;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VendaDTO {

    private LocalDateTime localDate;
    private double total;
    private TipoPagamento tipoPagamento;
    private List<ItemDTO> itens;


    public VendaDTO(Venda venda) {
        this.localDate = venda.getData_hora();
        this.total = venda.getTotal();
        this.tipoPagamento = venda.getTipoPagamento();
        this.itens = venda.getItens().stream().map(ItemDTO::new).toList();
    }
}
