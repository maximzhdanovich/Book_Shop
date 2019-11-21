package com.test.controller;


import com.test.db.model.Book;
import com.test.service.AuthorService;
import com.test.service.Author_ImageService;
import com.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class MainController extends BaseController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;


    @Autowired
    private Author_ImageService author_imageService;

    @GetMapping("/")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        if (filter != null && !filter.equals("")) {
            model.addAttribute("books", bookService.findByTitleEnOrTitleRu(filter, filter));
        } else {
            model.addAttribute("books", bookService.findAll());
        }
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/")
    public String add(Book book,
                      @RequestParam String author_surname,
                      @RequestParam String author_name,
                      @RequestParam MultipartFile image,
                      Model model
    ) throws IOException {
        author_imageService.add(image, authorService.findById(1));
        bookService.create(book, authorService.findBySurnameAndName(author_surname, author_name));
        model.addAttribute("books", bookService.findAll());
        return "main";
    }

}
