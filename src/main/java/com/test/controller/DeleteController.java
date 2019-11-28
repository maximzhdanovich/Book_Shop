package com.test.controller;

import com.test.db.model.User;
import com.test.service.BookService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/account/delete")
    public String deleteAccount(@AuthenticationPrincipal User user){
        userService.deleteById(userService.getCurrentUser(user).getId());
        return "redirect:/logout";
    }

}
