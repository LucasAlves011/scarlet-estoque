package com.scarlet.backscarlet.model.repository;

import com.scarlet.backscarlet.model.beans.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanhoRepository extends JpaRepository<Tamanho, String> {


}
