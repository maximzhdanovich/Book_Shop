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
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/deleteFromBasket")
    public ResponseEntity<Object> deleteBook(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Book book) {
        Basket basket = userService.getCurrentUser(user).getBasket();
        basket.getBooks().remove(bookService.findById(book.getId()));
        basketService.save(basket);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/bookToProcessing")
    public ResponseEntity<Object> bookToProcessing(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Book book) {
        Basket basket = userService.getCurrentUser(user).getBasket();
        basket.getBooks().remove(bookService.findById(book.getId()));
        basket.getBooksInProcessing().add(bookService.findById(book.getId()));
        basketService.save(basket);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("account/AllBookToProcessing")
    public ResponseEntity<Object> AllBookToProcessing(@AuthenticationPrincipal CustomUserDetail user) {
        Basket basket = userService.getCurrentUser(user).getBasket();
        basket.getBooksInProcessing().addAll(basket.getBooks());
        basket.getBooks().clear();
        basketService.save(basket);
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", basket.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
