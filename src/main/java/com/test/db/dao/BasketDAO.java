package com.test.db.dao;

import com.test.db.model.Basket;
import com.test.db.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BasketDAO extends CrudRepository<Basket, Long> {
    List<Basket> findAll();

    Basket findById(long id);
}
