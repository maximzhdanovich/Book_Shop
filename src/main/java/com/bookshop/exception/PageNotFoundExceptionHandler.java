package com.bookshop.exception;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.http.HTTPException;
import javax.xml.ws.spi.http.HttpExchange;

@ControllerAdvice
public class PageNotFoundExceptionHandler {

    //    @ExceptionHandler(value = PageNotFoundException.class)
    @ExceptionHandler(PageNotFoundException.class)
    public String notBook(Exception e) {
        return "notFound";
    }


}
