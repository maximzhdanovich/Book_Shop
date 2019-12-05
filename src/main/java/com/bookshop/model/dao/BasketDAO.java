package com.bookshop.model.dao;

import com.bookshop.model.entity.Basket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BasketDAO extends CrudRepository<Basket, Long> {
    List<Basket> findAll();

    Basket findById(long id);
}
