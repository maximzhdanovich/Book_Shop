package com.test.controller;

import com.test.db.model.Book;
import com.test.db.model.CustomUserDetail;
import com.test.service.BasketService;
import com.test.service.BookService;
import com.test.service.ServiceResponse;
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
//    @PostMapping("/addtobasket")
//    public String addToBasket(@AuthenticationPrincipal CustomUserDetail user, @RequestParam Map<String, String> form) {
//        basketService.addBooks(user,form);
//        return "redirect:/";
//    }


    @PostMapping("/saveBook")
    public ResponseEntity<Object> addBook(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Book book) {
        basketService.addSingleBook(user,bookService.findById(book.getId()));
        ServiceResponse<Long> response = new ServiceResponse<Long>("success", book.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
