package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.PersonNotFoundException;
import com.scarlet.backscarlet.model.beans.Produto;
import com.scarlet.backscarlet.model.dto.ProdutoAvulsoDTO;
import com.scarlet.backscarlet.model.dto.ProdutoNominalDTO;
import com.scarlet.backscarlet.model.dto.ProdutoNumericoDTO;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Object> findAllProdutos(){
        List<Object> retorno =  produtoRepository.findAllAvulso().stream().map(ProdutoAvulsoDTO::new).collect(Collectors.toList());
        retorno.addAll(produtoRepository.findAllNominal().stream().map(ProdutoNominalDTO::new).collect(Collectors.toList()));
        retorno.addAll(produtoRepository.findAllNumerico().stream().map(ProdutoNumericoDTO::new).collect(Collectors.toList()));
        return retorno;
    }

    public Object cadastrarProduto(Produto p){
        produtoRepository.save(p);

        if (p.getNominal() != null)
            return new ProdutoNominalDTO(p);
        else if (p.getAvulso() != null)
            return new ProdutoAvulsoDTO(p);
        else
            return new ProdutoNumericoDTO(p);
    }

    public void cadastrarProdutos(List<Produto> listaProdutos){
        produtoRepository.saveAll(listaProdutos);
    }

    public Object produtoPorId(int id) throws PersonNotFoundException {
        var x = produtoRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Produto não encontrado"));

        if (x.getNominal() != null)
            return new ProdutoNominalDTO(x);
        else if (x.getAvulso() != null)
            return new ProdutoAvulsoDTO(x);
        else
            return new ProdutoNumericoDTO(x);
    }

    // TODO pensar em mudanças de tipo como numerico -> nominal
    public Object alterarProduto(int id, Produto novoProduto) throws PersonNotFoundException{
        var antigoProduto = produtoRepository.findById(id).orElseThrow(() -> new PersonNotFoundException("Produto não encontrado"));

        antigoProduto.setNome(novoProduto.getNome());
        antigoProduto.setImagem(novoProduto.getImagem());
        antigoProduto.setCategoria(novoProduto.getCategoria());
        antigoProduto.setValor(novoProduto.getValor());
        
        if (antigoProduto.getAvulso() != null){
            antigoProduto.getAvulso().setQuantidade(novoProduto.getAvulso().getQuantidade());
        } else if (antigoProduto.getNominal() != null) {
            antigoProduto.getNominal().setP(novoProduto.getNominal().getP());
            antigoProduto.getNominal().setM(novoProduto.getNominal().getM());
            antigoProduto.getNominal().setG(novoProduto.getNominal().getG());
            antigoProduto.getNominal().setGG(novoProduto.getNominal().getGG());
        }else {
            antigoProduto.getNumerico().setT36(novoProduto.getNumerico().getT36());
            antigoProduto.getNumerico().setT38(novoProduto.getNumerico().getT38());
            antigoProduto.getNumerico().setT40(novoProduto.getNumerico().getT40());
            antigoProduto.getNumerico().setT42(novoProduto.getNumerico().getT42());
            antigoProduto.getNumerico().setT44(novoProduto.getNumerico().getT44());
            antigoProduto.getNumerico().setT46(novoProduto.getNumerico().getT46());
            antigoProduto.getNumerico().setT48(novoProduto.getNumerico().getT48());
            antigoProduto.getNumerico().setT50(novoProduto.getNumerico().getT50());
        }

        return  produtoRepository.save(antigoProduto);
    }

}
