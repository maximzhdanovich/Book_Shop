package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController  extends BaseController {
    @RequestMapping("/")
    public String home(Model model) {
        //model.addAttribute("users", userRepo.findAll());
        return "main";
    }
}
