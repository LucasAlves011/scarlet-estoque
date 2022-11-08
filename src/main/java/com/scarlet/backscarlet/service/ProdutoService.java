package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.model.beans.Categoria;
import com.scarlet.backscarlet.model.beans.Produto;
import com.scarlet.backscarlet.model.dto.produto.*;
import com.scarlet.backscarlet.model.repository.CategoriaRepository;
import com.scarlet.backscarlet.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
        var cats = p.getCategorias().stream().map(pr -> Optional.ofNullable(categoriaRepository.findByNome(pr.getNome()))
                .orElseThrow( () -> new ObjectNotFoundException("Categoria de nome "+ pr.getNome() + " não é uma categoria cadastrada.")))
                .collect(Collectors.toList());
        p.setCategorias(cats);
        produtoRepository.save(p);
        return transformarDTO(p);
    }

    public void cadastrarProdutos(List<Produto> listaProdutos){
        listaProdutos.forEach(this::cadastrarProduto);
    }

    public Object produtoPorId(int id) throws ObjectNotFoundException {
       var x = produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
       return transformarDTO(x);
    }

    public ProdutoDTO alterarProduto(int id, ProdutoInputDTO novoProduto) throws ObjectNotFoundException {
        var antigoProduto = produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));

        antigoProduto.setNome(novoProduto.getNome());
        antigoProduto.setImagem(novoProduto.getImagem());
        modificarCategoria(novoProduto.getCategorias(),antigoProduto);
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

        return  transformarDTO(produtoRepository.save(antigoProduto));
    }

    public List<ProdutoDTO> findByCategoria(String c){
        var categoria = new Categoria();
        categoria.setNome(c);
        return produtoRepository.findByCategorias(categoria).stream().map(this::transformarDTO).collect(Collectors.toList());
    }

    public ProdutoDTO alterarCategorias(int id,List<String> novasCategorias) throws ObjectNotFoundException {
        var x = produtoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Produto de id " + id + "não encontrado."));
        var todasCategorias = categoriaRepository.findAll();

        novasCategorias.forEach(c -> {
            if (!todasCategorias.stream().map(Categoria::toString).toList().contains(c.toUpperCase()))
                throw new ObjectNotFoundException("Categoria de nome "+ c + " não é uma categoria cadastrada.");
        });

        modificarCategoria(novasCategorias, x);

        return transformarDTO(x);
    }

    private List<Categoria> transformarStringsEmCategoria(List<String> novasCategorias) {
        var todasCategorias = categoriaRepository.findAll();
        return novasCategorias.stream().map(String::toUpperCase)
                .map(c -> todasCategorias.stream().filter(a -> a.verificarPorNome(c)).findFirst().get()).toList();
    }

    private void modificarCategoria(List<String> novasCategorias, Produto x) {
        var novasCats = transformarStringsEmCategoria(novasCategorias);

        var precisamSair = x.getCategorias().stream().filter(Predicate.not(novasCats::contains)).toList();

        var precisamEntrar = novasCats.stream().filter(Predicate.not(x.getCategorias()::contains)).toList();

        precisamSair.forEach(x::removerUmaCategoria);
        precisamEntrar.forEach(x::adicionarUmaCategoria);
    }

    private ProdutoDTO transformarDTO(Produto produto){
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
