package com.test.db.dao;

import com.test.db.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CategoryDAO extends CrudRepository<Category, Long> {
    Set<Category> findAll();

    Category findById(long id);

    Category findByTitleEn(String string);
    Category findFirstByTitleEnOrTitleRu(String titleEn,String titleRu);

    Category deleteById(long id);


}
