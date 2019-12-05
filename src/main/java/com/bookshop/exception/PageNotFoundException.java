package com.bookshop.exception;

public class PageNotFoundException extends RuntimeException {

    public PageNotFoundException() {
        super("my message");
    }

}
