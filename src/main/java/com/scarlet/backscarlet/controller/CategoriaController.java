package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController  {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<String>> getCategorias(){
       return ResponseEntity.ok().body(categoriaService.getCategorias());
    }

    @PostMapping
    public ResponseEntity<String> cadastrarCategoria(String categoria){
        return ResponseEntity.created(URI.create("/categoria/"+categoria)).body(categoriaService.cadastrarCategoria(categoria).toString());
    }

    @PutMapping
    public ResponseEntity<String> alterarCategoria(@PathParam("antigo") String antigo, @PathParam("novo") String novo) throws ObjectNotFoundException {
        return ResponseEntity.ok().body(categoriaService.alterarCategoria(antigo,novo));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarCategoria(@PathParam("categoria") String categoria) throws ObjectNotFoundException {
        categoriaService.deleteCategoria(categoria);
        return ResponseEntity.noContent().build();
    }

}
