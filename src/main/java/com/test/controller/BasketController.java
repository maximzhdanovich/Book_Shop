package com.test.controller;

import com.test.db.model.Book;
import com.test.db.model.CustomUserDetail;
import com.test.service.BasketService;
import com.test.service.BookService;
import com.test.service.ServiceResponse;
import com.test.service.UserService;
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

    @PostMapping("/deleteFromBasket")
    public ResponseEntity<Object> deleteBook(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Book book) {
        userService.getCurrentUser(user).getBasket().getBooks().remove(bookService.findById(book.getId()));
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
