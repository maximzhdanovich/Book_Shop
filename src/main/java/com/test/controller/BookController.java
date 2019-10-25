package com.test.controller;

import com.test.db.dao.BookDAO;
import com.test.db.dao.CategoryDAO;
import com.test.db.model.Book;
import com.test.db.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private CategoryDAO categoryDAO;

    @GetMapping
    public String bookList(Model model)
    {
        model.addAttribute("books",bookDAO.findAll());
        return "bookList";
    }
    @GetMapping("{book}")
    public String bookEdit(@PathVariable Book book, Model model){
        model.addAttribute("book",book);
        model.addAttribute("categories",book.getCategories());
        return "bookEdit";
    }

//    @PostMapping
//    public String bookSave(
//            @RequestParam String titleRu,
//            @RequestParam Map<String,String> form,
//            @RequestParam("bookId") Book book){
//        book.setTitleRu(titleRu);
//        Set<Category> categories = categoryDAO.findAll();
//        book.getCategories().clear();
//        for (String key : form.keySet()) {
//            if (categoryDAO.findByTitleEnOrTitleRu(key,key)!=null) {
//                book.getCategories().add(categoryDAO.findByTitleEnOrTitleRu(key,key));
//            }
//        }
//        bookDAO.save(book);
//        return "redirect:/book";
//    }
}
