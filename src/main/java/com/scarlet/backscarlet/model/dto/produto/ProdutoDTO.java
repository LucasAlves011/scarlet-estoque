package com.scarlet.backscarlet.model.dto.produto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Setter
@Getter
public abstract class ProdutoDTO {

    @Value("${app.pasta-fotos}")
    private String fotos;

    private int id;
    private String tipo;
    private String nome;
    private String marca;
    private List<String> categorias;
    private String imagem;
    private double valor;
    private int quantidade;

    public byte[] recuperarImagem(String nomeImagem) throws IOException {
        return Files.readAllBytes(Path.of(fotos+ nomeImagem));
    }

    public ProdutoDTO(int id,String nome, List<String> categorias, String imagemNome, double valor, int quantidade , String marca, String tipo) {

        this.imagem = imagemNome;
        this.id = id;
        this.nome = nome;
        this.categorias = categorias;
        this.valor = valor;
        this.quantidade = quantidade;
        this.marca = marca;
        this.tipo = tipo;
    }
}
