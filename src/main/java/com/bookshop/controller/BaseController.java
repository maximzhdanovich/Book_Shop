package com.bookshop.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

public class BaseController implements MessageSourceAware {

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
