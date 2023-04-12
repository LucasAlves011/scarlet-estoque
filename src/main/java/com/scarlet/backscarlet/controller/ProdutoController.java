package com.scarlet.backscarlet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.model.beans.Avulso;
import com.scarlet.backscarlet.model.beans.Nominal;
import com.scarlet.backscarlet.model.beans.Numerico;
import com.scarlet.backscarlet.model.dto.produto.ProdutoDTO;
import com.scarlet.backscarlet.model.dto.produto.ProdutoInputDTO;
import com.scarlet.backscarlet.model.dto.produto.RetornoQuantidadesPorMarca;
import com.scarlet.backscarlet.service.ProdutoService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/cadastro")
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestParam("produto") String produto , @RequestParam("imagem") MultipartFile imagem , @RequestParam String tipo) throws JsonProcessingException {

        // TODO : COLOCAR TODAS ESSAS LÓGICAS NO SERVICE
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(produto);
        var nome = node.get("nome").asText();
        var marca = node.get("marca").asText();
        var valor = node.get("valor").asDouble();
        List<String> categorias = new ArrayList<>();
        var cat = node.get("categorias");
        for (JsonNode elementNode : cat) {
            String value = elementNode.asText();
            categorias.add(value);
        }

        if ( !node.get("avulso").isNull() && !node.get("numerico").isNull() || !node.get("avulso").isNull() && !node.get("nominal").isNull() || !node.get("numerico").isNull() && !node.get("nominal").isNull() ){
            throw new ObjectNotFoundException("Produto deve ter apenas um tipo de tamanho");
        }

        ProdutoInputDTO input = null;

        switch (tipo.toUpperCase()){
            case "NOMINAL":
                var p = node.get("nominal").get("p").asInt();
                var m = node.get("nominal").get("m").asInt();
                var g = node.get("nominal").get("g").asInt();
                var gg = node.get("nominal").get("gg").asInt();
                input = new ProdutoInputDTO(nome,marca,valor,categorias,tipo,new Nominal(p,m,g,gg),null,null);
                break;
            case "NUMERICO":
                var t36 = node.get("numerico").get("t36").asInt();
                var t38 = node.get("numerico").get("t38").asInt();
                var t40 = node.get("numerico").get("t40").asInt();
                var t42 = node.get("numerico").get("t42").asInt();
                var t44 = node.get("numerico").get("t44").asInt();
                var t46 = node.get("numerico").get("t46").asInt();
                var t48 = node.get("numerico").get("t48").asInt();
                var t50 = node.get("numerico").get("t50").asInt();
                input = new ProdutoInputDTO(nome,marca,valor,categorias,tipo,null,new Numerico(t36,t38,t40,t42,t44,t46,t48,t50),null);
                break;
            case "AVULSO":
                var quantidade = node.get("avulso").asInt();
                input = new ProdutoInputDTO(nome,marca,valor,categorias,tipo,null,null, new Avulso(quantidade));
                break;
        }


        return ResponseEntity.created(URI.create("/produto")).body(produtoService.cadastrarProduto(input,imagem));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable int id) throws ObjectNotFoundException {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().build();
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

    @GetMapping(value = "/nome/{id}")
    public ResponseEntity<String> getNomeProdutoPorId(@PathVariable int id) throws ObjectNotFoundException {
       return ResponseEntity.ok().body(produtoService.nomeProdutoPorId(id));
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
}