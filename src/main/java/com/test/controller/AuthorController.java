package com.test.controller;

import com.test.db.model.Author;
import com.test.service.AuthorService;
import com.test.service.Author_ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private Author_ImageService authorImageService;

    @GetMapping("{author}")
    public String authorEdit(@PathVariable Author author, Model model) {
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @PostMapping("{authorId}")
    public String authorSave(@RequestParam String surname, @RequestParam String name, @RequestParam("authorId") Author author){
        return "redirect:/author";
    }

    @GetMapping
    public String authorList(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("images",authorImageService.findAll());
        return "authorList";
    }

    @PostMapping
    public String addAuthor(@RequestParam String surname,
                            @RequestParam String name,
                            @RequestParam MultipartFile image) throws IOException {
        authorService.create(surname,name);
        authorImageService.add(image, authorService.findBySurnameAndName(surname,name));
        return "redirect:/author";
    }

    @GetMapping("/{authorId}/books")
    public String authorBooks(Model model, @PathVariable String authorId){
        model.addAttribute("books",authorService.findById(Long.parseLong(authorId)).getBooks());
        return "main";
    }
}
