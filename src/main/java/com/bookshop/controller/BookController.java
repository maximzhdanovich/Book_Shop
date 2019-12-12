package com.bookshop.controller;

import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.service.AuthorService;
import com.bookshop.service.BookService;
import com.bookshop.service.Book_ImageService;
import com.bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
//
//    @Autowired
//    private Book_ImageService bookImageService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String bookList(Model model, /*@AuthenticationPrincipal CustomUserDetail user,*/
                           @PageableDefault(value = 12) Pageable pageable) {
        model.addAttribute("url", "/book");
        model.addAttribute("page", bookService.findAllPage(pageable));
        return "bookList";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin/create")
    public String bookCreate(@RequestParam Double price,
                             @RequestParam String titleRu,
                             @RequestParam String titleEn,
                             @RequestParam String authorSurname,
                             @RequestParam String authorName,
                             @RequestParam String description,
                             @RequestParam MultipartFile image) throws IOException {
        if (!authorService.findBySurnameAndName(authorSurname, authorName).isPresent()) {
            return "redirect:/book";
        }
        bookService.create(price, titleRu, titleEn, description, authorService.findBySurnameAndName(authorSurname, authorName).get(), image);
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
    @PostMapping("/admin/save")
    public String bookSave(
            @RequestParam String titleRu,
            @RequestParam String titleEn,
            @RequestParam String authorSurname,
            @RequestParam String authorName,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            @RequestParam Map<String, String> form,
            @RequestParam("bookId") Book book) throws IOException {
        if (!authorService.findBySurnameAndName(authorSurname, authorName).isPresent()) {
            return "redirect:/book/admin/" + book.getId();
        }
        bookService.update(book, titleEn, titleRu, authorSurname, authorName, description, form, image);
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

//    @PostMapping("/admin/add")
//    public String add(Book book,
//                      @RequestParam String author_surname,
//                      @RequestParam String author_name,
////                      Model model,
//                      @RequestParam MultipartFile book_image) throws IOException {
//        bookService.create(book, authorService.findBySurnameAndName(author_surname, author_name).get());
//        bookImageService.add(book_image, book);
////        model.addAttribute("books", bookService.findAll());
//        return "redirect:/book";
//    }

    private boolean bookIsNull(Book byId) {
        return byId == null;
    }
}
