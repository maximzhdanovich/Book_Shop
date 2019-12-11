package com.bookshop.model.dao;

import com.bookshop.model.entity.Author_Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Author_ImageDAO extends CrudRepository<Author_Image, Long> {
    List<Author_Image> findAll();

    Author_Image findById(long id);


    void deleteById(long id);
}
