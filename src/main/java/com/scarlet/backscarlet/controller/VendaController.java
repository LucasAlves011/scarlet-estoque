package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.controller.exceptions.ProdutoNotFoundException;
import com.scarlet.backscarlet.service.VendaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping()
    public void vender() throws ProdutoNotFoundException {
//        vendaService.vender(null);
    }

}
