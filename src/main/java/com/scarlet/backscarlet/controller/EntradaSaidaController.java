package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.model.beans.SolicitarItem;
import com.scarlet.backscarlet.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/io")
public class EntradaSaidaController {

    private final VendaService vendaService;

    public EntradaSaidaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping()
    public ResponseEntity<Boolean> verificarItensRetirarEstoque(@RequestBody List<SolicitarItem> solicitarItemList )  {
        System.out.println(solicitarItemList);
        return ResponseEntity.ok().body(vendaService.verificarSeItens(solicitarItemList));
    }

}
