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
    public String authorSave(@RequestParam String surname, @RequestParam String name, @RequestParam("authorId") Author author) {
        authorService.update(surname, name, author);
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
    public String authorBooks(@RequestParam(required = false, defaultValue = "") String filter, Model model, @PathVariable String authorId) {
        if (filter != null && !filter.equals("")) {
            model.addAttribute("books", filter(authorService.findById(Long.parseLong(authorId)).getBooks(), filter));
        } else {
            model.addAttribute("books", authorService.findById(Long.parseLong(authorId)).getBooks());
        }
        model.addAttribute("books", authorService.findById(Long.parseLong(authorId)).getBooks());
        return "bookList";
    }

    private List<Book> filter(List<Book> books, String filter) {
        for (Book book : books) {
            if (!book.getTitleEn().equals(filter) && !book.getTitleRu().equals(filter))
                books.remove(book);
        }
        return books;
    }
}
