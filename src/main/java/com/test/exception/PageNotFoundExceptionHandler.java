package com.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PageNotFoundExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String  notBook(Exception e) {
        return "notFound";
    }
}
