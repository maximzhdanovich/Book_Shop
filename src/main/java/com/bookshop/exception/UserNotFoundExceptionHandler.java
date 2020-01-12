package com.bookshop.exception;

import com.bookshop.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class UserNotFoundExceptionHandler {

    @Autowired
    private BookController bookController;

    @ExceptionHandler(UserNotFoundException.class)
    public String notBook(Exception e) {
        return "userNotFound";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String notCorrectInput(Exception e, Model model, HttpServletRequest httpServletRequest, @PageableDefault(value = 12) Pageable pageable) {
        if(httpServletRequest.getRequestURL().indexOf("book")+4!=httpServletRequest.getRequestURL().length())
            return "redirect:/book";
        model.addAttribute("qwe", "qwe");
        return bookController.bookList(model,pageable);
    }
}
