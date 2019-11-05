package com.test.controller;

import com.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@PreAuthorize("hasAnyRole('ADMIN')")
@Controller
public class DeleteController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book/delete/{id}")
    public String deletePerson(@PathVariable long id) {
        bookService.deleteById(id);
        return "redirect:/book";
    }

}
