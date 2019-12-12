package com.bookshop.controller;

import com.bookshop.model.entity.Category;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Autowired
    private BookService bookService;

    @GetMapping
    public String allCategory(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categoryList";
    }

    @GetMapping("/{category}")
    public String singleCategory(@PathVariable Category category, Model model, @PageableDefault(size = 12) Pageable pageable) {
        model.addAttribute("categoryPage", "");
        model.addAttribute("category", category);
        model.addAttribute("page", bookService.findAllByCategories(category, pageable));
        model.addAttribute("url", "/category/" + category.getId());
        return "bookList";
    }
}
