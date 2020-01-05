package com.bookshop.controller;

import com.bookshop.model.entity.Basket;
import com.bookshop.model.entity.Book;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.service.BasketService;
import com.bookshop.service.BookService;
import com.bookshop.service.ServiceResponse;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    @PostMapping("/saveBook")
    public ResponseEntity<Object> addBook(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Book book) {
        basketService.addSingleBook(user, bookService.findById(book.getId()));
        ServiceResponse<Long> response = new ServiceResponse<>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/deleteFromBasket")
    public ResponseEntity<Object> deleteBook(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Book book) {
        basketService.deleteBookFromBasket(user,book);
        ServiceResponse<Long> response = new ServiceResponse<>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/bookToProcessing")
    public ResponseEntity<Object> bookToProcessing(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Book book) {
        basketService.sendBookToProcessing(user,book);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/AllBookToProcessing")
    public ResponseEntity<Object> AllBookToProcessing(@AuthenticationPrincipal CustomUserDetail user) {
        basketService.sendAllBookToProcessing(user);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", user.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
