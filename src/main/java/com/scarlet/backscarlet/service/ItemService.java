package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.controller.exceptions.TamanhoIncompativelException;
import com.scarlet.backscarlet.model.enums.Tamanho;
import com.scarlet.backscarlet.model.repository.ItemRepository;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProdutoRepository produtoRepository;

    public ItemService(ItemRepository itemRepository,ProdutoRepository produtoRepository) {
        this.itemRepository = itemRepository;
        this.produtoRepository = produtoRepository;
    }


    public boolean verificarDisponibilidade(int produtoId, Tamanho tamanho, int unidades) throws ObjectNotFoundException , TamanhoIncompativelException {

        var produto = produtoRepository.findById(produtoId).orElseThrow(() -> new ObjectNotFoundException("Produto de Id '"+ produtoId +"' não encontrado"));

        if (tamanho == null) {
            if (produto.getAvulso() == null ) throw  new TamanhoIncompativelException("Não é possível adicionar o tamanho " + Optional.ofNullable(tamanho.getTamanho()).orElse("")
                    + "para um produto que esta cadastrado como um avulso.");
            return produto.getAvulso().getQuantidade() >= unidades;
        }
        else if (tamanho.equals(Tamanho.P) || tamanho.equals(Tamanho.M) | tamanho.equals(Tamanho.G) || tamanho.equals(Tamanho.GG)  ) {
            if (produto.getNominal() == null ) throw  new TamanhoIncompativelException("Não é possível adicionar o tamanho " + tamanho.getTamanho()
                    + " para um produto que esta cadastrado com tamanhos (36/38/40/42...)");

            if (Tamanho.P == tamanho) return produto.getNominal().getP() >= unidades;
            else if (Tamanho.M == tamanho) return produto.getNominal().getM() >= unidades;
            else if (Tamanho.G == tamanho) return produto.getNominal().getG() >= unidades;
            return produto.getNominal().getGG() >= unidades;
        }else if (tamanho.equals(Tamanho.T36) || tamanho.equals(Tamanho.T38) | tamanho.equals(Tamanho.T40) || tamanho.equals(Tamanho.T42) ||
                tamanho.equals(Tamanho.T44) || tamanho.equals(Tamanho.T46) | tamanho.equals(Tamanho.T48) || tamanho.equals(Tamanho.T50) ){
            if (produto.getNumerico() == null ) throw  new TamanhoIncompativelException("Não é possível adicionar o tamanho " + tamanho.getTamanho()
                    + " para um produto que esta cadastrado com tamanhos (P/M/G/GG)");

            if (Tamanho.T36 == tamanho) return produto.getNumerico().getT36() >= unidades;
            else if (Tamanho.T38 == tamanho) return produto.getNumerico().getT38() >= unidades;
            else if (Tamanho.T40 == tamanho) return produto.getNumerico().getT40() >= unidades;
            else if (Tamanho.T42 == tamanho) return produto.getNumerico().getT42() >= unidades;
            else if (Tamanho.T44 == tamanho) return produto.getNumerico().getT44() >= unidades;
            else if (Tamanho.T46 == tamanho) return produto.getNumerico().getT46() >= unidades;
            else if (Tamanho.T48 == tamanho) return produto.getNumerico().getT48() >= unidades;
            else return produto.getNumerico().getT50() >= unidades;
        }

        throw new RuntimeException("Erro estranho"); // TODO ajeitar os textos das exceptions
    }

}
