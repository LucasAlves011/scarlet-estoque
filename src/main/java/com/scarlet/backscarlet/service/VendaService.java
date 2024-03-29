package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.controller.exceptions.UnidadesIndisponiveisException;
import com.scarlet.backscarlet.model.beans.SolicitarItem;
import com.scarlet.backscarlet.model.beans.Tamanho;
import com.scarlet.backscarlet.model.enums.TamanhoEnum;
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

                System.out.println(solicitarItem.getTamanho().toString());

                switch (solicitarItem.getTamanho().toString()) {
                    case "P" -> produto.retirarEstoque(tamanhosEmMemoria.get(0), solicitarItem.getQuantidade());
                    case "AVULSO" -> produto.retirarEstoque(tamanhosEmMemoria.get(1), solicitarItem.getQuantidade());
                    case "M" -> produto.retirarEstoque(tamanhosEmMemoria.get(2), solicitarItem.getQuantidade());
                    case "G" -> produto.retirarEstoque(tamanhosEmMemoria.get(3), solicitarItem.getQuantidade());
                    case "GG" -> produto.retirarEstoque(tamanhosEmMemoria.get(4), solicitarItem.getQuantidade());
                    case "T36" -> produto.retirarEstoque(tamanhosEmMemoria.get(5), solicitarItem.getQuantidade());
                    case "T38" -> produto.retirarEstoque(tamanhosEmMemoria.get(6), solicitarItem.getQuantidade());
                    case "T40" -> produto.retirarEstoque(tamanhosEmMemoria.get(7), solicitarItem.getQuantidade());
                    case "T42" -> produto.retirarEstoque(tamanhosEmMemoria.get(8), solicitarItem.getQuantidade());
                    case "T44" -> produto.retirarEstoque(tamanhosEmMemoria.get(9), solicitarItem.getQuantidade());
                    case "T46" -> produto.retirarEstoque(tamanhosEmMemoria.get(10), solicitarItem.getQuantidade());
                    case "T48" -> produto.retirarEstoque(tamanhosEmMemoria.get(11), solicitarItem.getQuantidade());
                    case "T50" -> produto.retirarEstoque(tamanhosEmMemoria.get(12), solicitarItem.getQuantidade());
                }
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
//        List.of("P", "M", "G", "GG", "T36", "T38", "T40", "T42", "T44", "T46", "T48", "T50", "AVULSO").stream().filter(t -> t.equals(solicitarItem.getTamanho().toString())).findFirst().get();
        switch (solicitarItem.getTamanho().toString()) {
            case "P" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.P, solicitarItem.getQuantidade());
            }
            case "AVULSO" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.AVULSO, solicitarItem.getQuantidade());
            }
            case "M" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.M, solicitarItem.getQuantidade());
            }
            case "G" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.G, solicitarItem.getQuantidade());
            }
            case "GG" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.GG, solicitarItem.getQuantidade());
            }
            case "T36" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.T36, solicitarItem.getQuantidade());
            }
            case "T38" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.T38, solicitarItem.getQuantidade());
            }
            case "T40" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.T40, solicitarItem.getQuantidade());
            }
            case "T42" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.T42, solicitarItem.getQuantidade());
            }
            case "T44" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.T44, solicitarItem.getQuantidade());
            }
            case "T46" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.T46, solicitarItem.getQuantidade());
            }
            case "T48" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.T48, solicitarItem.getQuantidade());
            }
            case "T50" -> {
                return produto.verificarDisponibilidade(TamanhoEnum.T50, solicitarItem.getQuantidade());
            }

        }


        return produto.verificarDisponibilidade(solicitarItem.getTamanho(), solicitarItem.getQuantidade());
    }


}
