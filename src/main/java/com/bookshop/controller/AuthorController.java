package com.bookshop.controller;

import com.bookshop.exception.PageNotFoundException;
import com.bookshop.model.entity.Author;
import com.bookshop.service.AuthorImageService;
import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private AuthorImageService authorImageService;

    @Autowired
    private BookService bookService;

    @GetMapping("admin/{authorId}")
    public String authorEditPage(@PathVariable("authorId") Author author, Model model) {
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @PostMapping("admin/{authorId}")
    public String authorSaveEditedInformation(@RequestParam String surname, @RequestParam String name, @RequestParam("authorId") Author author,
                                              @RequestParam MultipartFile image) throws IOException {
        authorService.update(surname, name, author, image);
        return "redirect:/author";
    }

    @GetMapping
    public String authorList(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authorList";
    }

    @PostMapping
    public String addAuthor(@RequestParam String surname,
                            @RequestParam String name,
                            @RequestParam MultipartFile image,
                            Model model) throws IOException {
        if (authorService.findBySurnameAndName(surname, name).isPresent()) {
            model.addAttribute("authorError", "author is already exist");
            model.addAttribute("authors", authorService.findAll());

            return "authorList";
        }
        authorService.create(surname, name);
        authorImageService.add(image, authorService.findBySurnameAndName(surname, name).get());
        return "redirect:/author";
    }

    @GetMapping("/{author}/books")
    public String authorBooks(Model model, @PathVariable Author author, @PageableDefault(size = 12) Pageable pageable) {
        model.addAttribute("page", bookService.findAllByAuthor(author, pageable));
        model.addAttribute("authorPage", "");
        model.addAttribute("author", author);
        model.addAttribute("url", "/author/" + author.getId() + "/books");
        return "bookList";
    }

    @GetMapping("/{some}")
    public String notFound(@PathVariable String some){
        throw new PageNotFoundException();
    }


}
