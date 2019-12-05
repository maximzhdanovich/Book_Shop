package com.bookshop.controller;

import com.bookshop.model.entity.Author;
import com.bookshop.model.entity.Book;
import com.bookshop.service.AuthorService;
import com.bookshop.service.Author_ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private Author_ImageService authorImageService;

    @GetMapping("admin/{authorId}")
    public String authorEdit(@PathVariable("authorId") Author author, Model model) {
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @PostMapping("admin/{authorId}")
    public String authorSave(@RequestParam String surname, @RequestParam String name, @RequestParam("authorId") Author author,
                             @RequestParam MultipartFile image) throws IOException {
        authorService.update(surname, name, author,image);
        return "redirect:/author";
    }

    @GetMapping
    public String authorList(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("images", authorImageService.findAll());
        return "authorList";
    }

    @PostMapping
    public String addAuthor(@RequestParam String surname,
                            @RequestParam String name,
                            @RequestParam MultipartFile image) throws IOException {
        authorService.create(surname, name);
        authorImageService.add(image, authorService.findBySurnameAndName(surname, name));
        return "redirect:/author";
    }

    @GetMapping("/{authorId}/books")
    public String authorBooks(Model model, @PathVariable Author authorId) {
        model.addAttribute("books", authorId.getBooks());
        model.addAttribute("authorPage","");
        model.addAttribute("author",authorId);
        return "bookList";
    }


}
