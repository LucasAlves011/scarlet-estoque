package com.scarlet.backscarlet.model.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity(name = "categoria")
public class Categoria {
    @Id
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    List<Produto> produtos;

    @Override
    public String toString() {
        return nome;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {
    }

    public boolean verificarPorNome(String nome) {
        return nome.equals(this.nome);
    }
}
