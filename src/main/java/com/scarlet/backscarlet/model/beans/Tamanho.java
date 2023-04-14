package com.scarlet.backscarlet.model.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity(name = "tamanho")
@ToString
public class Tamanho {

    @Id
    private String tamanho;

    public boolean verificarPorNome(String nome){
        return nome.equals(this.tamanho);
    }
}
