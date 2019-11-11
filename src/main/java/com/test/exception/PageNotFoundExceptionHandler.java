package com.test.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PageNotFoundExceptionHandler {

    @ExceptionHandler(PageNotFoundException.class)
    public String  notBook(PageNotFoundException e) {
        return "forward:/pageNotFound";
    }
}
