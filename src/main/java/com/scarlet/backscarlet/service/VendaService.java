package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.controller.exceptions.UnidadesIndisponiveisException;
import com.scarlet.backscarlet.model.beans.Item;
import com.scarlet.backscarlet.model.beans.SolicitarItem;
import com.scarlet.backscarlet.model.beans.Tamanho;
import com.scarlet.backscarlet.model.beans.Venda;
import com.scarlet.backscarlet.model.dto.VendaDTO;
import com.scarlet.backscarlet.model.enums.TipoPagamento;
import com.scarlet.backscarlet.model.repository.ItemRepository;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import com.scarlet.backscarlet.model.repository.TamanhoRepository;
import com.scarlet.backscarlet.model.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final List<Tamanho> tamanhosEmMemoria;
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemRepository itemRepository;

    public VendaService(VendaRepository vendaRepository,ProdutoRepository produtoRepository,ItemRepository itemRepository, TamanhoRepository tamanhoRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.itemRepository = itemRepository;
        this.tamanhosEmMemoria = tamanhoRepository.findAll();
    }

    public VendaDTO fecharVenda(List<SolicitarItem> itens, TipoPagamento tipoPagamento) throws ObjectNotFoundException{
        var x = itens.stream().map(this::transformarSolicitarEmItem).collect(Collectors.toList());
        var venda = new Venda();
        venda.setItens(x);
        venda.setTipoPagamento(tipoPagamento);
        venda.setTotal(x.stream().mapToDouble((c) -> c.getProduto().getValor()*c.getUnidades()).sum());
        x.stream().map(Item::getProduto).forEach(produtoRepository::save);
        venda.setData_hora(LocalDateTime.now());
        itemRepository.saveAll(x);
        vendaRepository.save(venda);
        x.forEach(Item::retirarDoEstoque);
        return new VendaDTO(venda);
    }

    private Item transformarSolicitarEmItem(SolicitarItem solicitarItem){
        var produto = produtoRepository.findById(solicitarItem.getIdProduto()).orElseThrow(
                () -> new ObjectNotFoundException("Produto de id '"+ solicitarItem.getIdProduto() +"' não encontrado."));

        var tamanho = tamanhosEmMemoria.stream().filter( t -> t.verificarPorNome(solicitarItem.getTamanho().toString())).findFirst().get();

        if (produto.verificarDisponibilidade(solicitarItem.getTamanho(),solicitarItem.getQuantidade()))
            return new Item(produto, solicitarItem.getQuantidade(), tamanho);
        else
            throw new UnidadesIndisponiveisException("Unidades do produto '" +produto.getNome()+"' no tamanho '" + solicitarItem.getTamanho()+"' são insuficientes");
    }

}
