package com.scarlet.backscarlet.model.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


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
