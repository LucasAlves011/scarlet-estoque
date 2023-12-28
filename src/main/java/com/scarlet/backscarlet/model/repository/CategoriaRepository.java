package com.scarlet.backscarlet.model.repository;

import com.scarlet.backscarlet.model.beans.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria findByNomeOrderByNome(String nome);

    @Query(value = "SELECT produto_id,array_to_string(array_agg(produto_categorias.categoria_nome),';') as tamanhos FROM produto_categorias where produto_id in (:ids) GROUP BY produto_id ", nativeQuery = true)
    List<String> findAllProdutoWithCategorias(List<Integer> ids);
}
