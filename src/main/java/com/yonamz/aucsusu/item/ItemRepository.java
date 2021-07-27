package com.yonamz.aucsusu.item;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where category=:category")
    List<Item> findByCategory(String category);

    List<Item> findByWriterContaining(String keyword);
    List<Item> findByTitleContaining(String keyword);
}
