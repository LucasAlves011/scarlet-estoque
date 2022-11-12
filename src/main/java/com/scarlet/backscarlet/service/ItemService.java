package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.controller.exceptions.TamanhoIncompativelException;
import com.scarlet.backscarlet.model.enums.TamanhoEnum;
import com.scarlet.backscarlet.model.repository.ItemRepository;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;


@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProdutoRepository produtoRepository;

    public ItemService(ItemRepository itemRepository,ProdutoRepository produtoRepository) {
        this.itemRepository = itemRepository;
        this.produtoRepository = produtoRepository;
    }


    public boolean verificarDisponibilidade(int produtoId, TamanhoEnum tamanhoEnum, int unidades) throws ObjectNotFoundException , TamanhoIncompativelException {
        return produtoRepository.findById(produtoId).orElseThrow(() -> new ObjectNotFoundException("Produto de Id '"+ produtoId +"' não encontrado"))
                .verificarDisponibilidade(tamanhoEnum,unidades);
    }

}
