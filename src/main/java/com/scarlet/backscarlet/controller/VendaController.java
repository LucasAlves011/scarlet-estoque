package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.model.beans.SolicitarItem;
import com.scarlet.backscarlet.model.dto.VendaDTO;
import com.scarlet.backscarlet.model.enums.TipoPagamento;
import com.scarlet.backscarlet.service.VendaService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping()
    public ResponseEntity<VendaDTO> verificarSeItem(@PathParam("tipoPagamento") TipoPagamento tipoPagamento , @RequestBody List<SolicitarItem> solicitarItemList )  {
        return ResponseEntity.ok().body(vendaService.fecharVenda(solicitarItemList,tipoPagamento));
    }

}
