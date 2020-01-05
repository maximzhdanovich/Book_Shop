package com.bookshop.model.dataService;

import com.bookshop.model.dao.BasketDAO;
import com.bookshop.model.entity.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasketDataService {

    @Autowired
    private BasketDAO basketDAO;

    public List<Basket> findAll() {
        return basketDAO.findAll();
    }

    public Basket findById(long id) {
        return basketDAO.findById(id);
    }

    public void save(Basket basket) {
        basketDAO.save(basket);
    }
}
