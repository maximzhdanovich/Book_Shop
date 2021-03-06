package com.bookshop.model.dao;

import com.bookshop.model.entity.AuthorImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorImageDAO extends CrudRepository<AuthorImage, Long> {

    List<AuthorImage> findAll();

    AuthorImage findByAuthorId(long id);

    void deleteByAuthorId(long id);
}
