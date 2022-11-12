package com.scarlet.backscarlet.model.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "tamanho")
public class Tamanho {

    @Id
    private String tamanho;

    public boolean verificarPorNome(String nome){
        return nome.equals(this.tamanho);
    }
}
