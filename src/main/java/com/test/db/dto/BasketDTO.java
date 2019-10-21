package com.test.db.dto;

import com.test.db.dao.IDAO;
import com.test.db.model.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class BasketDTO implements IDTO<Basket> {
    @Autowired
    @Qualifier("basketDAO")
    private IDAO<Basket> basketDAO;

    @Override
    public void add(Basket entity) {
        basketDAO.add(entity);
    }

    @Override
    public void update(Basket entity) {
        basketDAO.update(entity);
    }

    @Override
    public void delete(Basket entity) {
        basketDAO.delete(entity);
    }

    @Override
    public Basket getOnId(Long id) {
        return basketDAO.getOnId(id);
    }

    @Override
    public List<Basket> findAll() {
        return basketDAO.findAll();
    }

    @Override
    public void deleteOnId(Long id) {
        basketDAO.deleteOnId(id);
    }
}
