package com.test.controller;


import com.test.db.model.*;
import com.test.exception.PageNotFoundException;
import com.test.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    private Author_ImageService author_imageService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        List<Book> books;

        if (filter != null && !filter.equals("")) {
            books = bookService.findByTitleEnOrTitleRu(filter, filter);
        } else {
            books = bookService.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/")
    public String add(@RequestParam double price,
                      @RequestParam String titleRu,
                      @RequestParam String titleEn,
                      @RequestParam String author_surname,
                      @RequestParam String author_name,
                      @RequestParam MultipartFile image,
                      Model model
    ) throws IOException {
        if (image != null && !image.getOriginalFilename().isEmpty()) {
            Author_Image author_image = new Author_Image();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String fileName = uuidFile  + image.getOriginalFilename();
            image.transferTo(new File(uploadPath+"/"+fileName));
            author_image.setAuthorImage(fileName);
            author_image.setAuthor(authorService.findById(1));
            author_imageService.save(author_image);
        }
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

//    @GetMapping("/pageNotFound")
//    public String pageNotFound(){
//        return "notFound";
//    }
//
//    @GetMapping("/{some}")
//    public String somepage(){
//        throw new PageNotFoundException();
//    }
}
