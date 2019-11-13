package com.test.controller;

import com.test.db.model.Author;
import com.test.service.AuthorService;
import com.test.service.Author_ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String authorSave(@RequestParam String surname,
                             @RequestParam String name,
                             @RequestParam("authorId") Author author){
        author.setSurname(surname);
        author.setName(name);
        authorService.save(author);
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
                            Model model) {
        Author author = new Author(surname, name);
        authorService.save(author);
        return "redirect:/author";
    }
}
