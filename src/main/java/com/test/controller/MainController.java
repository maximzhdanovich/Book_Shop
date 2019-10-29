package com.test.controller;


import com.test.db.model.Book;
import com.test.service.AuthorService;
import com.test.service.BookService;
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
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        List<Book> books = bookService.findAll();

        if (filter != null && !filter.equals("")) {
            books = bookService.findByTitleEnOrTitleRu(filter, filter);
        } else {
            books = bookService.findAll();
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

        Book book = new Book(price, titleRu, titleEn);
            book.setAuthor(authorService.findBySurnameAndName(author_surname,author_name));

        bookService.save(book);

        List<Book> books = bookService.findAll();

        model.put("books", books);

        return "main";
    }
}
