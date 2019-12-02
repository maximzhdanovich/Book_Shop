package com.test.controller;


import com.test.db.model.Book;
import com.test.service.AuthorService;
import com.test.service.BookService;
import com.test.service.Book_ImageService;
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
    private Book_ImageService bookImageService;

    @GetMapping("/")
    public String main( Model model) {
        model.addAttribute("books",bookService.findAll());
        model.addAttribute("authors",authorService.findAll());
        return "main";
    }
//
//    @PostMapping("/book/add")
//    public String add(Book book,
//                      @RequestParam String author_surname,
//                      @RequestParam String author_name,
//                      Model model,
//                      @RequestParam MultipartFile book_image) throws IOException  {
//        bookService.create(book, authorService.findBySurnameAndName(author_surname, author_name));
//        bookImageService.add(book_image, book);
//        model.addAttribute("books", bookService.findAll());
//        return "bookList";
//    }
//
//    @GetMapping("/filter")
//    public String filter(@RequestParam(required = false, defaultValue = "") String filter,@RequestParam String url, Model model,HttpServletRequest request){
//        request.getRequestURI();
//        return "redirect:/";
//    }

}
