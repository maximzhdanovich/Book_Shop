package com.test.controller;


import com.test.db.model.*;
import com.test.exception.PageNotFoundException;
import com.test.service.AuthorService;
import com.test.service.BasketService;
import com.test.service.BookService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController extends BaseController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @GetMapping("/")
    public String main(@AuthenticationPrincipal CustomUserDetail user,
                       @RequestParam(required = false, defaultValue = "") String filter, Model model) {
        List<Book> books;

        if (filter != null && !filter.equals("")) {
            books = bookService.findByTitleEnOrTitleRu(filter, filter);
        } else {
            books = bookService.findAll();
        }
        if (getCurrentUser(user) == null) {
            return "redirect:/login";
        }
        model.addAttribute("currentUser", getCurrentUser(user));
        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam double price,
            @RequestParam String titleRu,
            @RequestParam String titleEn,
            @RequestParam String author_surname,
            @RequestParam String author_name,
            Model model
    ) {
        Book book = new Book(price, titleRu, titleEn);
        Author bySurnameAndName = authorService.findBySurnameAndName(author_surname, author_name);
        book.setAuthor(bySurnameAndName);
        bookService.save(book);
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "main";
    }

    @PostMapping("/addtobasket")
    public String addToBasket(@AuthenticationPrincipal CustomUserDetail user, @RequestParam Map<String, String> form) {
        Basket basket = getCurrentUser(user).getBasket();
        for (String id : form.keySet()) {
            basket.getBooks().add(bookService.findById(Long.valueOf(id)));
        }
        basketService.save(basket);
        return "redirect:/";
    }

    private User getCurrentUser(@AuthenticationPrincipal CustomUserDetail user) {
        if (user.getId() == null) {
            return null;
        }
        return userService.findById(user.getId());
    }

    @GetMapping("/pageNotFound")
    public String pageNotFound(){
        return "notFound";
    }

    @GetMapping("/{some}")
    public String somepage(){
        throw new PageNotFoundException();
    }
}
