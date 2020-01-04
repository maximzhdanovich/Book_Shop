package com.bookshop.model.dao;

import com.bookshop.model.entity.Book_Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Book_ImageDao extends CrudRepository<Book_Image, Long> {

    List<Book_Image> findAll();

    Book_Image findById(long id);

    void deleteById(long id);

}
