package com.bookshop.controller;

import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
import com.bookshop.service.CartService;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String myAccount(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        model.addAttribute("user", userService.getCurrentUser(customUserDetail));
        return "account";
    }

    @GetMapping("/edit")
    public String accountEdit(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser(customUserDetail));
        return "accountEdit";
    }

    @PostMapping("/edit")
    public String saveAccountEdit(@AuthenticationPrincipal CustomUserDetail customUserDetail,
                                  @Valid User newUserInformation,
                                  BindingResult bindingResult,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("repeatNewPassword") String repeatNewPassword,
                                  Model model) {
        return userService.userEditConfiguration(customUserDetail, newUserInformation, bindingResult, newPassword, repeatNewPassword, model);
    }

    @GetMapping("/cart")
    public String myBasket(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        model.addAttribute("user", userService.getCurrentUser(customUserDetail));
        model.addAttribute("books", userService.getCurrentUser(customUserDetail).getCart().getBooks());
        model.addAttribute("approvedBooks", userService.getCurrentUser(customUserDetail).getCart().getBooksApproved());
        return "myBasket";
    }


}
