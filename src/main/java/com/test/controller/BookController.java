package com.test.controller;

import com.test.db.dao.AuthorDAO;
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
    private AuthorDAO authorDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @GetMapping
    public String bookList(Model model) {
        model.addAttribute("books", bookDAO.findAll());
        return "bookList";
    }

    @GetMapping("{book}")
    public String bookEdit(@PathVariable Book book, Model model) {
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryDAO.findAll());
        return "bookEdit";
    }

    @PostMapping
    public String bookSave(
            @RequestParam String titleRu,
            @RequestParam String titleEn,
            @RequestParam String authorSurname,
            @RequestParam String authorName,
            @RequestParam Map<String, String> form,
            @RequestParam("bookId") Book book) {
        book.setTitleRu(titleRu);
        book.setTitleEn(titleEn);
        if (authorDAO.findBySurnameAndName(authorSurname, authorName) == null) {
            return "redirect:/book/" + book.getId();
        }
        book.setAuthor(authorDAO.findBySurnameAndName(authorSurname, authorName));
        Set<Category> categories = categoryDAO.findAll();
        book.getCategories().clear();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                book.getCategories().add(categoryDAO.findById(Integer.valueOf(s)));
            }
        }
        bookDAO.save(book);
        return "redirect:/book";
    }
}
