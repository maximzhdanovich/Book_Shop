package com.test.controller;


import com.test.db.model.Book;
import com.test.db.model.Book_Image;
import com.test.service.AuthorService;
import com.test.service.Author_ImageService;
import com.test.service.BookService;
import com.test.service.Book_ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private Book_ImageService bookImageService;

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
                      Model model,
                      @RequestParam MultipartFile book_image) throws IOException  {
        bookService.create(book, authorService.findBySurnameAndName(author_surname, author_name));
        bookImageService.add(book_image, book);
        model.addAttribute("books", bookService.findAll());
        return "main";
    }

}
