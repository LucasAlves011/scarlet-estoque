package com.scarlet.backscarlet.service;

import com.scarlet.backscarlet.controller.exceptions.ObjectNotFoundException;
import com.scarlet.backscarlet.model.beans.Categoria;
import com.scarlet.backscarlet.model.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<String> getCategorias(){
       return categoriaRepository.findAll().stream().map(Categoria::toString).collect(Collectors.toList());
    }

    public Categoria cadastrarCategoria(String categoria) throws IllegalArgumentException{
        verificarValidadeCategoria(categoria);

        var x = new Categoria();
        x.setNome(categoria.toUpperCase());
        return categoriaRepository.save(x);
    }

    private void verificarValidadeCategoria(String categoria) {
        if (categoria == null || categoria.isBlank())
             throw new InvalidParameterException("Categoria não pode ser vazia.");
        if (categoria.matches("\\W+"))
            throw new InvalidParameterException("Categoria não pode conter caracteres especiais.");
        if (categoriaRepository.findAll().stream().map(Categoria::toString).anyMatch(a -> a.equals(categoria.toUpperCase())))
            throw new IllegalArgumentException("Categoria de nome '" + categoria + "' já existe. Tente utilizá-la.");
    }

    public void deleteCategoria(String nome) throws ObjectNotFoundException {
        var x = Optional.ofNullable(categoriaRepository.findByNomeOrderByNome(nome))
                .orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada de nome'" + nome + "' não encontrada."));
        categoriaRepository.delete(x);
    }

    public String alterarCategoria(String nomeAntigo,String nomeNovo) throws ObjectNotFoundException,IllegalArgumentException{
        verificarValidadeCategoria(nomeNovo);
        var x = Optional.ofNullable(categoriaRepository.findByNomeOrderByNome(nomeAntigo)).orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada de nome'" + nomeAntigo +"' não encontrada."));
        x.setNome(nomeNovo.toUpperCase());
        categoriaRepository.save(x);
        return x.toString();
    }

}
