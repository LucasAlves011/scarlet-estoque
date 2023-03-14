package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.service.feignInterfaces.VendaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class TesteFeign  {

    @Autowired
    private VendaInterface vendaInterface;

    @GetMapping()
    public String teste() {
        return "Resposta do estoque";
    }

    @GetMapping("/chamada")
    public String chamada() {
        System.out.println("Chamando a interface venda...");
        return vendaInterface.teste();
    }
}
