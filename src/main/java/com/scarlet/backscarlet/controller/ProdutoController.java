package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.controller.exceptions.ProdutoNotFoundException;
import com.scarlet.backscarlet.model.beans.Produto;
import com.scarlet.backscarlet.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> getProdutos(){
        return ResponseEntity.ok().body(produtoService.findAllProdutos());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarProduto(@RequestBody Produto p){
        produtoService.cadastrarProduto(p);
        return ResponseEntity.created(URI.create("/produto"+p.getId())).body(p);
    }

    @PostMapping("/*")
    public ResponseEntity<Object> cadastrarProdutos(@RequestBody List<Produto> lista){
        produtoService.cadastrarProdutos(lista);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProdutoPorId(@PathVariable int id) throws ProdutoNotFoundException {
       return ResponseEntity.ok().body(produtoService.produtoPorId(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> alterarProduto(@PathVariable int id, @RequestBody Produto p) throws ProdutoNotFoundException {
        return ResponseEntity.ok().body(produtoService.alterarProduto(id,p));
    }


}