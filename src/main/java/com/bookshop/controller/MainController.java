package com.bookshop.controller;


import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import com.bookshop.service.Book_ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends BaseController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private Book_ImageService bookImageService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("books", bookService.lastBook());
        model.addAttribute("authors", authorService.findAll());
        return "main";
    }


}
