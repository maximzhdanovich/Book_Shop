package com.test.db.dao;


import com.test.db.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorDAO extends CrudRepository<Author, Long> {
    List<Author> findAll();

    Author findById(long id);

    Author findFirstBySurnameAndName(String surname, String name);
}
