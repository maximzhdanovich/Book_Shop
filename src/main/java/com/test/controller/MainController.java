package com.test.controller;


import com.test.db.dao.AuthorDAO;
import com.test.db.dao.BookDAO;
import com.test.db.dao.CategoryDAO;
import com.test.db.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController extends BaseController {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private AuthorDAO authorDAO;
    @Autowired
    private CategoryDAO categoryDAO;


    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Book> books = bookDAO.findAll();

        if (filter != null && !filter.equals("")) {
            books = bookDAO.findByTitleEnOrTitleRu(filter, filter);
        } else {
            books = bookDAO.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @RequestParam double price,
            @RequestParam String titleRu,
            @RequestParam String titleEn,
            @RequestParam String author_surname,
            @RequestParam String author_name,
            Map<String, Object> model
    ) {

        Book book = new Book(price, titleRu, titleEn, authorDAO.findFirstBySurnameAndName(author_surname,author_name)
                , categoryDAO.findAll());

        bookDAO.save(book);

        List<Book> books = bookDAO.findAll();

        model.put("books", books);

        return "main";
    }
}
