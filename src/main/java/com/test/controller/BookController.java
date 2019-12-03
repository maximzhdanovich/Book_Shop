package com.test.controller;

import com.test.db.model.Book;
import com.test.service.AuthorService;
import com.test.service.BookService;
import com.test.service.Book_ImageService;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private Book_ImageService bookImageService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String bookList(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        if (filter != null && !filter.equals("")) {
            model.addAttribute("books", bookService.findByTitleEnOrTitleRu(filter, filter));
        } else {
            model.addAttribute("books", bookService.findAll());
        }
        model.addAttribute("filter", filter);
        return "bookList";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public String bookCreate(@RequestParam Double price,
                             @RequestParam String titleRu,
                             @RequestParam String titleEn,
                             @RequestParam String authorSurname,
                             @RequestParam String authorName,
                             @RequestParam String description,
                             @RequestParam MultipartFile image) throws IOException {
        if (authorService.findBySurnameAndName(authorSurname, authorName) == null) {
            return "redirect:/book";
        }
        bookService.create(price, titleRu, titleEn, description,authorService.findBySurnameAndName(authorSurname, authorName),image);
        return "redirect:/book";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin/{book}")
    public String bookEdit(@PathVariable String book, Model model) {
        Book byId = bookService.findById(Long.parseLong(book));
        if (bookIsNull(byId)) return "redirect:/book";
        model.addAttribute("book", byId);
        model.addAttribute("categories", categoryService.findAll());
        return "bookEdit";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/save")
    public String bookSave(
            @RequestParam String titleRu,
            @RequestParam String titleEn,
            @RequestParam String authorSurname,
            @RequestParam String authorName,
            @RequestParam String description,
            @RequestParam Map<String, String> form,
            @RequestParam("bookId") Book book) {
        if (authorService.findBySurnameAndName(authorSurname, authorName) == null) {
            return "redirect:/book/" + book.getId();
        }
        bookService.update(book, titleEn, titleRu, authorSurname, authorName, description, form);
        return "redirect:/book";
    }

    @GetMapping("/{book}")
    public String book(@PathVariable String book, Model model) {
        Book byId = bookService.findById(Long.parseLong(book));
        if (bookIsNull(byId)) return "redirect:/book";
        model.addAttribute("book", byId);
        model.addAttribute("categories", categoryService.findAll());
        return "book";
    }

    @PostMapping("/add")
    public String add(Book book,
                      @RequestParam String author_surname,
                      @RequestParam String author_name,
                      Model model,
                      @RequestParam MultipartFile book_image) throws IOException {
        bookService.create(book, authorService.findBySurnameAndName(author_surname, author_name));
        bookImageService.add(book_image, book);
        model.addAttribute("books", bookService.findAll());
        return "bookList";
    }

    private boolean bookIsNull(Book byId) {
        return byId == null;
    }
}
