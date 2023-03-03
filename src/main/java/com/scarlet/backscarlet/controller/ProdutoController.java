package com.scarlet.backscarlet.controller;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.model.dto.produto.DTOteste;
import com.scarlet.backscarlet.model.dto.produto.ProdutoDTO;
import com.scarlet.backscarlet.model.dto.produto.ProdutoInputDTO;
import com.scarlet.backscarlet.model.dto.produto.RetornoQuantidadesPorMarca;
import com.scarlet.backscarlet.service.ProdutoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping( consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestParam ProdutoInputDTO produto , @RequestParam MultipartFile image){
        return ResponseEntity.created(URI.create("/produto")).body(produtoService.cadastrarProduto(produto,image));
    }

//    @PostMapping("/*")
//    public ResponseEntity<Object> cadastrarProdutos(@RequestBody List<Produto> lista){
//        produtoService.cadastrarProdutos(lista);
//        return ResponseEntity.ok().body(null);
//    }

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

    @GetMapping(value = "/marcas")
    public ResponseEntity<List<String>> findMarcas(){
        return ResponseEntity.ok().body(produtoService.findMarcas());
    }

    @GetMapping(value = "/quantidade-marcas")
    public ResponseEntity<List<RetornoQuantidadesPorMarca>> getSeparados(){
        return ResponseEntity.ok().body(produtoService.marcasAndQuantidade());
    }

    @GetMapping(value = "/marca/{marca}" )
    public ResponseEntity<List<ProdutoDTO>> findProdutosByMarca(@PathVariable String marca){
        return ResponseEntity.ok().body(produtoService.findByMarca(marca));
    }
    @GetMapping(value = "/imagem/{imagem}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> findImage(@PathVariable String imagem) throws IOException {
        return ResponseEntity.ok().body(produtoService.findImage(imagem));
    }


/*    @PostMapping(path = "/teste", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Void> a( @RequestPart Teste objeto, @RequestPart MultipartFile imagem){

        try {
            byte[] bytes = imagem.getBytes();
            Path caminhoImagem = Paths.get("C:\\Users\\lucas\\OneDrive\\Imagens\\Imagens Scarlet\\" + UUID.randomUUID()+".png");
            Files.write(caminhoImagem, bytes);
        } catch (IOException e) {
            System.err.println("Erro em cadastrar a imagem do produto.");
        }
        System.out.println(objeto);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/imagem/{imagem}" ,produces = MediaType.IMAGE_PNG_VALUE )
    public byte[] x(@PathVariable String imagem) throws IOException{
        return Files.readAllBytes(Path.of("C:\\Users\\lucas\\OneDrive\\Imagens\\Imagens Scarlet\\"+imagem));
    }*/

    @PostMapping(value = "/teste" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> teste(@RequestBody DTOteste produto, @RequestBody(required = false) String x){
//        System.out.println(x);
        System.out.println(produto.getCategorias());
        System.out.println(produto.getMarca());
        System.out.println(produto.getValor());
        System.out.println(produto.getNome());
        return ResponseEntity.ok().body(null);
    }
}