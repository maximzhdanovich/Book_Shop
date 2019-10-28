package com.test.db.dao;


import com.test.db.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface AuthorDAO extends CrudRepository<Author, Long> {
    Set<Author> findAll();

    Author findById(long id);
    void deleteById(long id);
    Author findBySurnameAndName(String surname, String name);
    Set<Author> findBySurnameOrName(String surname, String name);
}
