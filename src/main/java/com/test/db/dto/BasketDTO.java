package com.test.db.dto;

import com.test.db.dao.BasketDAO;
import com.test.db.model.Basket;
import com.test.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasketDTO {
    @Autowired
    private BasketDAO basketDAO;

    public List<Basket> findAll() {
        return basketDAO.findAll();
    }

    public Basket findById(long id) {
        return basketDAO.findById(id);
    }

    public void save(Basket basket){
        basketDAO.save(basket);
    }
}
