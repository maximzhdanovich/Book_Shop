package com.bookshop.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String notBook(Exception e) {
        return "userNotFound";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String notCorrectInput(Exception e, Model model) {
        model.addAttribute("qwe", "qweq");
        return "bookList";
    }
}
