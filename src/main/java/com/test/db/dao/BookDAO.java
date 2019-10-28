package com.test.db.dao;


import com.test.db.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


public interface BookDAO extends CrudRepository<Book, Long> {
    List<Book> findAll();

    Book findById(long id);

    Book deleteById(long id);

    List<Book> findAllByPrice(double price);

    List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu);
}
