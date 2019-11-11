package com.test.controller;

import com.test.db.model.Book;
import com.test.db.model.Category;
import com.test.exception.PageNotFoundException;
import com.test.service.AuthorService;
import com.test.service.BookService;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String bookList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "bookList";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{book}")
    public String bookEdit(@PathVariable Book book, Model model) {
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.findAll());
        return "bookEdit";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public String bookSave(
            @RequestParam String titleRu,
            @RequestParam String titleEn,
            @RequestParam String authorSurname,
            @RequestParam String authorName,
            @RequestParam Map<String, String> form,
            @RequestParam("bookId") Book book) {
        book.setTitleRu(titleRu);
        book.setTitleEn(titleEn);
        if (authorService.findBySurnameAndName(authorSurname, authorName) == null) {
            return "redirect:/book/" + book.getId();
        }
        book.setAuthor(authorService.findBySurnameAndName(authorSurname, authorName));
        List<Category> categories = categoryService.findAll();
        book.getCategories().clear();
        for (String s : form.keySet()) {
            if (form.get(s).equals("on")) {
                book.getCategories().add(categoryService.findById(Integer.valueOf(s)));
            }
        }
        bookService.save(book);
        return "redirect:/book";
    }

}
