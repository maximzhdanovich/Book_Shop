package com.test.db.dao;


import com.test.db.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BookDAO extends CrudRepository<Book, Long> {
    List<Book> findAll();

    Book findById(long id);

    void deleteById(long id);

    List<Book> findAllByPrice(double price);

    List<Book> findByTitleEnOrTitleRu(String titleEn, String titleRu);
}
