package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.model.enums.TamanhoEnum;
import com.scarlet.backscarlet.service.ItemService;
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
    public boolean verificarDisponibilidade(int produtoId, TamanhoEnum tamanhoEnum, int unidades){
        return itemService.verificarDisponibilidade(produtoId, tamanhoEnum,unidades);
    }




}
