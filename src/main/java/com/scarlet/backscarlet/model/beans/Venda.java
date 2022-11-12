package com.scarlet.backscarlet.model.beans;

import com.scarlet.backscarlet.model.enums.TipoPagamento;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(updatable = false)
    private LocalDateTime data_hora;

    private double total;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo")
    private TipoPagamento tipoPagamento;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Item.class)
    @JoinTable(name = "venda_itens",joinColumns = @JoinColumn(name = "venda_id")
            ,inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itens;

}
