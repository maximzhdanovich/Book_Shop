package com.test.controller;

import com.test.db.dao.BookDAO;
import com.test.db.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController  extends BaseController {

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping("/")
    public String home(Model model) {
        return "main";
    }
}
