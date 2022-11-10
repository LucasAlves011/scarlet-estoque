package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.model.enums.Tamanho;
import com.scarlet.backscarlet.model.repository.ItemRepository;
import com.scarlet.backscarlet.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/item")
public class ItemController {



    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public boolean verificarDisponibilidade(int produtoId, Tamanho tamanho, int unidades){
        return itemService.verificarDisponibilidade(produtoId,tamanho,unidades);
    }




}
