package com.test.db.dao;

import com.test.db.model.Book;
import com.test.db.model.Book_Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Book_ImageDao  extends CrudRepository<Book_Image, Long> {

    List<Book_Image> findAll();

    Book_Image findById(long id);

    Book_Image findByBook(Book book);

    void deleteById(long id);

}
