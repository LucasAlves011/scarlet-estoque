package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.model.beans.Produto;
import com.scarlet.backscarlet.model.dto.produto.ProdutoDTO;
import com.scarlet.backscarlet.model.dto.produto.ProdutoInputDTO;
import com.scarlet.backscarlet.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody Produto p){
        return ResponseEntity.created(URI.create("/produto"+p.getId())).body(produtoService.cadastrarProduto(p));
    }

    @PostMapping("/*")
    public ResponseEntity<Object> cadastrarProdutos(@RequestBody List<Produto> lista){
        produtoService.cadastrarProdutos(lista);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> alterarProduto(@PathVariable int id, @RequestBody ProdutoInputDTO p) throws ObjectNotFoundException {
        return ResponseEntity.ok().body(produtoService.alterarProduto(id,p));
    }

    @GetMapping
    public ResponseEntity<List<Object>> getProdutos(){
        return ResponseEntity.ok().body(produtoService.findAllProdutos());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProdutoPorId(@PathVariable int id) throws ObjectNotFoundException {
       return ResponseEntity.ok().body(produtoService.produtoPorId(id));
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<ProdutoDTO>> findByCategoria(@PathParam("categoria") String categoria){
        return ResponseEntity.ok().body(produtoService.findByCategoria(categoria));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ProdutoDTO>> findProdutoByNome(@PathParam("nome") String nome){
        return ResponseEntity.ok().body(produtoService.findByNome(nome));
    }

    @PutMapping(value = "/{id}/categorias")
    public ResponseEntity<ProdutoDTO> alterarCategorias(@PathVariable int id, @RequestBody ArrayList<String> categorias) throws ObjectNotFoundException {
        return ResponseEntity.ok().body(produtoService.alterarCategorias(id,categorias));
    }
}