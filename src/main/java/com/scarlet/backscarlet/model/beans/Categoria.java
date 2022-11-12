package com.scarlet.backscarlet.model.beans;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "categoria")
public class Categoria {
    @Id
    private String nome;

    @Override
    public String toString() {
        return  nome ;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {
    }

    public boolean verificarPorNome(String nome){
        return nome.equals(this.nome);
    }
}
