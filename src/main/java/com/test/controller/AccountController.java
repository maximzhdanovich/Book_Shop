package com.test.controller;

import com.test.db.model.CustomUserDetail;
import com.test.service.BasketService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private BasketService basketService;

    @GetMapping
    public String myAccount(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model){
        model.addAttribute("user",userService.getCurrentUser(customUserDetail));
        return "account";
    }

    @GetMapping("/basket")
    public String myBasket(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model){
        model.addAttribute("user",userService.getCurrentUser(customUserDetail));
        model.addAttribute("books",basketService.getByUser(customUserDetail).getBooks());
        return "myBasket";
    }
}
