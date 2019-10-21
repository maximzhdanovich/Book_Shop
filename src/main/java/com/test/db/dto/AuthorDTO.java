package com.test.db.dto;

import com.test.db.dao.IDAO;
import com.test.db.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class AuthorDTO implements IDTO<Author> {
    @Autowired
    @Qualifier("authorDAO")
    private IDAO<Author> authorDAO;
    @Override
    public void add(Author entity) {
        authorDAO.add(entity);
    }

    @Override
    public void update(Author entity) {
        authorDAO.update(entity);
    }

    @Override
    public void delete(Author entity) {
        authorDAO.delete(entity);
    }

    @Override
    public Author getOnId(Long id) {
        return authorDAO.getOnId(id);
    }

    @Override
    public List<Author> findAll() {
        return authorDAO.findAll();
    }

    @Override
    public void deleteOnId(Long id) {
        authorDAO.deleteOnId(id);
    }
}
