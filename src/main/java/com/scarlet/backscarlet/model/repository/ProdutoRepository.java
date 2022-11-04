package com.scarlet.backscarlet.model.repository;

import com.scarlet.backscarlet.model.beans.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    @Query("select p from produto p join fetch  p.nominal")
    List<Produto> findAllNominal();

    @Query("select p from produto p join fetch  p.numerico")
    List<Produto> findAllNumerico();

    @Query("select p from produto p join fetch  p.avulso")
    List<Produto> findAllAvulso();

}
