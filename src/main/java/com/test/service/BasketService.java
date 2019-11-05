package com.test.service;

import com.test.db.dto.BasketDTO;
import com.test.db.model.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    @Autowired
    private BasketDTO basketDTO;

    public List<Basket> findAll() {
        return basketDTO.findAll();
    }

    public Basket findById(long id) {
        return basketDTO.findById(id);
    }

    public void save(Basket basket) {
        basketDTO.save(basket);
    }
}
