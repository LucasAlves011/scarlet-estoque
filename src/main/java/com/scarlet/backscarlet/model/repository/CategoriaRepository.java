package com.scarlet.backscarlet.model.repository;

import com.scarlet.backscarlet.model.beans.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

    Categoria findByNomeOrderByNome(String nome);
}
