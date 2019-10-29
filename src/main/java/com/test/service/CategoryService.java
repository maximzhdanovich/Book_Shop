package com.test.service;

import com.test.db.dto.CategoryDTO;
import com.test.db.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryDTO categoryDTO;

    public List<Category> findAll() {
        return categoryDTO.findAll();
    }

    public Category findById(long id) {
        return categoryDTO.findById(id);
    }

    public Category findByTitleEn(String string) {
        return categoryDTO.findByTitleEn(string);
    }

    public Category findFirstByTitleEnOrTitleRu(String titleEn, String titleRu) {
        return categoryDTO.findFirstByTitleEnOrTitleRu(titleEn, titleRu);
    }
}
