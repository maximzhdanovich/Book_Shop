package com.test.db.dao;

import com.test.db.model.Author;
import com.test.db.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorDAO extends CrudRepository<Author, Long> {
    List<Author> findAll();

    Author findById(long id);

    Author deleteById(long id);

    Author findBySurnameAndName(String surname,String name);
    List<Author> findAllBySurname(String surname);

    Author deleteBySurnameAndName(String surname,String name);
    List<Author> deleteAllBySurnameAndName(String surname);


}
