package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.controller.exceptions.UnidadesIndisponiveisException;
import com.scarlet.backscarlet.model.beans.SolicitarItem;
import com.scarlet.backscarlet.model.beans.Tamanho;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import com.scarlet.backscarlet.model.repository.TamanhoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    private final List<Tamanho> tamanhosEmMemoria;
    private final ProdutoRepository produtoRepository;

    public VendaService(ProdutoRepository produtoRepository, TamanhoRepository tamanhoRepository) {
        this.produtoRepository = produtoRepository;
        this.tamanhosEmMemoria = tamanhoRepository.findAll();
    }

    public boolean verificarSeItens(List<SolicitarItem> itens) throws ObjectNotFoundException, UnidadesIndisponiveisException {
        if (itens.stream().allMatch(this::verificarSeItem)) {
            //retirar produtos do estoque
            itens.stream().forEach(solicitarItem -> {
                var produto = produtoRepository.findById(solicitarItem.getProdutoId()).orElseThrow(
                        () -> new ObjectNotFoundException("Produto de id '" + solicitarItem.getProdutoId() + "' não encontrado."));

                var tamanho = tamanhosEmMemoria.stream().filter(t -> t.verificarPorNome(solicitarItem.getTamanho().toString())).findFirst().get();

                produto.retirarEstoque(tamanho, solicitarItem.getQuantidade());
                produtoRepository.save(produto);
            });
            return true;
        }
        return false;
    }

    private boolean verificarSeItem(SolicitarItem solicitarItem) throws ObjectNotFoundException, UnidadesIndisponiveisException {
        var produto = produtoRepository.findById(solicitarItem.getProdutoId()).orElseThrow(
                () -> new ObjectNotFoundException("Produto de id '" + solicitarItem.getProdutoId() + "' não encontrado."));

//        var tamanho = tamanhosEmMemoria.stream().filter( t -> t.verificarPorNome(solicitarItem.getTamanho().toString( ))).findFirst().get();
        List.of("P", "M", "G", "GG", "T36", "T38", "T40", "T42", "T44", "T46", "T48", "T50", "AVULSO").stream().filter(t -> t.equals(solicitarItem.getTamanho().toString())).findFirst().get();
        return produto.verificarDisponibilidade(solicitarItem.getTamanho(), solicitarItem.getQuantidade());
    }


}
