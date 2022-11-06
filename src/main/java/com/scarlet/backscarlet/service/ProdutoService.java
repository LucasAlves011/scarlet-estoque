package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ProdutoNotFoundException;
import com.scarlet.backscarlet.model.beans.Categoria;
import com.scarlet.backscarlet.model.beans.Produto;
import com.scarlet.backscarlet.model.dto.ProdutoAvulsoDTO;
import com.scarlet.backscarlet.model.dto.ProdutoDTO;
import com.scarlet.backscarlet.model.dto.ProdutoNominalDTO;
import com.scarlet.backscarlet.model.dto.ProdutoNumericoDTO;
import com.scarlet.backscarlet.model.repository.CategoriaRepository;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Object> findAllProdutos(){
        List<Produto> retorno =  produtoRepository.findAllAvulso();
        retorno.addAll(produtoRepository.findAllNominal());
        retorno.addAll(produtoRepository.findAllNumerico());
        return retorno.stream().map(this::transformarDTO).collect(Collectors.toList());
    }

    public ProdutoDTO cadastrarProduto(Produto p){
        //Todo Retornar uma exceção quando não encontrar uma categoria
        var cats = p.getCategorias().stream().map(pr -> categoriaRepository.findByNome(pr.getNome())).collect(Collectors.toList());
        p.setCategorias(cats);
        produtoRepository.save(p);
        return transformarDTO(p);
    }

    public void cadastrarProdutos(List<Produto> listaProdutos){
        listaProdutos.forEach(this::cadastrarProduto);
//        produtoRepository.saveAll(listaProdutos);
    }

    public Object produtoPorId(int id) throws ProdutoNotFoundException {
        var x = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
       return transformarDTO(x);
    }

    // TODO pensar em mudanças de tipo como numerico -> nominal
    public Object alterarProduto(int id, Produto novoProduto) throws ProdutoNotFoundException {
        var antigoProduto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        antigoProduto.setNome(novoProduto.getNome());
        antigoProduto.setImagem(novoProduto.getImagem());
        antigoProduto.setCategorias(novoProduto.getCategorias());
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

    public List<ProdutoDTO> findByCategoria(Categoria c){
        return produtoRepository.findByCategorias(c).stream().map(this::transformarDTO).collect(Collectors.toList());
    }

    public ProdutoDTO transformarDTO(Produto produto){
        if (produto.getNominal() != null)
            return new ProdutoNominalDTO(produto);
        else if (produto.getAvulso() != null)
            return new ProdutoAvulsoDTO(produto);
        else if(produto.getNumerico() != null)
            return new ProdutoNumericoDTO(produto);
        else
            return null; //TODO retornar uma exception com uma erro específico
    }

}
