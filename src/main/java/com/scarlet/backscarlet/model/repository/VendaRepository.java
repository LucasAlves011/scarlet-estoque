package com.scarlet.backscarlet.model.repository;

import com.scarlet.backscarlet.model.beans.Venda;
import com.scarlet.backscarlet.model.enums.TamanhoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda,Integer> {
}
