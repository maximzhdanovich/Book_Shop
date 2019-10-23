package com.test.db.dao;

import com.test.db.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDAO extends CrudRepository<Category, Long> {
    List<Category> findAll();

    Category findById(long id);

    Category deleteById(long id);

    List<Category> findByTitleEnOrTitleRu(String price);

    List<Category> deleteByTitleEnOrTitleRu(String price);


}
