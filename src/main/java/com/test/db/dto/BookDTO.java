package com.test.db.dto;

import com.test.db.dao.IDAO;
import com.test.db.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class BookDTO implements IDTO<Book> {
    @Autowired
    @Qualifier("bookDAO")
    private IDAO<Book> bookDAO;

    @Override
    public void add(Book entity) {
        bookDAO.add(entity);
    }

    @Override
    public void update(Book entity) {
        bookDAO.update(entity);
    }

    @Override
    public void delete(Book entity) {
        bookDAO.delete(entity);
    }

    @Override
    public Book getOnId(Long id) {
        return bookDAO.getOnId(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public void deleteOnId(Long id) {
        bookDAO.deleteOnId(id);
    }
}