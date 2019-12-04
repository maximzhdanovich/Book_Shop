package com.test.controller;

import com.test.db.model.Category;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String allCategory(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "categoryList";
    }

    @GetMapping("/{category}")
    public String singleCategory(@PathVariable Category category,Model model){
        model.addAttribute("books",category.getBooks());
        return "bookList";
    }
}
