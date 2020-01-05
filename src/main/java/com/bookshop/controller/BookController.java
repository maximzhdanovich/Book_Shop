package com.bookshop.controller;

import com.bookshop.model.entity.Book;
import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String bookList(Model model, /*@AuthenticationPrincipal CustomUserDetail user,*/
                           @PageableDefault(value = 12) Pageable pageable) {
        model.addAttribute("url", "/book");
        model.addAttribute("page", bookService.findAllPage(pageable));
        model.addAttribute("categories", categoryService.findAll());
        return "bookList";
    }



    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping/*("/admin/create")*/
    public String bookCreate(
            @RequestParam MultipartFile image,
            @RequestParam Map<String, String> form,
            Model model) throws IOException {
        if (!authorService.findBySurnameAndName(form.get("authorSurname"), form.get("authorName")).isPresent()) {
            return "redirect:/book";
        }
        bookService.create(authorService.findBySurnameAndName(form.get("authorSurname"), form.get("authorName")).get(), form, image);
        model.addAttribute("bookAdd", "");
        return "redirect:/book";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/admin/{book}")
    public String bookEdit(@PathVariable String book, Model model) {
        Book byId = bookService.findById(Long.parseLong(book));
        if (bookIsNull(byId))
            return "redirect:/book";
        model.addAttribute("book", byId);
        model.addAttribute("categories", categoryService.findAll());
        return "bookEdit";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin/save")
    public String bookSaveEdit(
            @RequestParam MultipartFile image,
            @RequestParam Map<String, String> form,
            @RequestParam("bookId") Book book) throws IOException {
        if (!authorService.findBySurnameAndName(form.get("authorSurname"), form.get("authorName")).isPresent()) {
            return "redirect:/book/admin/" + book.getId();
        }
//        bookService.update(book, titleEn, titleRu, authorSurname, authorName, description, form, image);
        bookService.update(book, form, image);
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

    private boolean bookIsNull(Book byId) {
        return byId == null;
    }
}
