package com.bookshop.controller;


import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/")
    public String main(Model model) throws IOException, SolrServerException {
        model.addAttribute("books", bookService.getLastBooks());
        model.addAttribute("authors", authorService.findAll());
        return "main";
    }

}
