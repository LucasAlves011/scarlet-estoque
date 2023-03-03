package com.scarlet.backscarlet.model.dto.produto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Setter
@Getter
public abstract class ProdutoDTO {

    private int id;
    private String tipo;
    private String nome;
    private String marca;
    private List<String> categorias;
    private String imagem;
    private double valor;
    private int quantidade;

    public byte[] recuperarImagem(String nomeImagem) throws IOException {
        return Files.readAllBytes(Path.of("C:\\Users\\lucas\\OneDrive\\Imagens\\Imagens Scarlet\\"+ nomeImagem));
    }

    public ProdutoDTO(int id,String nome, List<String> categorias, String imagemNome, double valor, int quantidade , String marca, String tipo) {
//        try{
//            this.imagem = recuperarImagem(imagemNome);
//        }catch (IOException e){
//            this.imagem = null;
//        }

        this.imagem = imagemNome;
//        this.imagem = "https://via.placeholder.com/150";
        this.id = id;
        this.nome = nome;
        this.categorias = categorias;
        this.valor = valor;
        this.quantidade = quantidade;
        this.marca = marca;
        this.tipo = tipo;
    }
}
