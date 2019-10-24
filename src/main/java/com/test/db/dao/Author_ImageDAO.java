package com.test.db.dao;

import com.test.db.model.Author;
import com.test.db.model.Author_Image;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface Author_ImageDAO extends CrudRepository<Author_Image, Long> {
    Set<Author_Image> findAll();

    Author_Image findById(long id);

    Author_Image findByAuthor(Author author);

    Author_Image deleteById(long id);
}
