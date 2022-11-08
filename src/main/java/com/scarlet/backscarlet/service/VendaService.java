package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.model.repository.ItemRepository;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import com.scarlet.backscarlet.model.repository.VendaRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    public VendaService(VendaRepository vendaRepository,ProdutoRepository produtoRepository,ItemRepository itemRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.itemRepository = itemRepository;
    }

//    public void vender(List<SolicitarItem> ids) throws ProdutoNotFoundException {
//        List<Produto> produtos = ids.stream().map(id -> produtoRepository.findById(id.getIdProduto()).orElseThrow(ProdutoNotFoundException::new)).toList();
//
//    }
}
