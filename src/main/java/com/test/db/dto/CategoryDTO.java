package com.test.db.dto;

import com.test.db.dao.CategoryDAO;
import com.test.db.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDTO{
    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> findAll(){
        return categoryDAO.findAll();
    }

    public Category findById(long id){
        return categoryDAO.findById(id);
    }

    public Category findByTitleEn(String string){
        return categoryDAO.findByTitleEn(string);
    }

    public Category findFirstByTitleEnOrTitleRu(String titleEn, String titleRu){
        return categoryDAO.findFirstByTitleEnOrTitleRu(titleEn,titleRu);
    }
}
