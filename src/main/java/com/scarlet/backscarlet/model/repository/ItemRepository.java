package com.scarlet.backscarlet.model.repository;

import com.scarlet.backscarlet.model.beans.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
