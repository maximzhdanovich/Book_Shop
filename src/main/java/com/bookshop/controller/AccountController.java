package com.bookshop.controller;

import com.bookshop.exception.PageNotFoundException;
import com.bookshop.model.entity.CustomUserDetail;
import com.bookshop.model.entity.User;
import com.bookshop.service.BasketService;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

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

    @GetMapping("/basket")
    public String myBasket(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
            model.addAttribute("user", userService.getCurrentUser(customUserDetail));
            model.addAttribute("books", userService.getCurrentUser(customUserDetail).getBasket().getBooks());
            model.addAttribute("approvedBooks", userService.getCurrentUser(customUserDetail).getBasket().getBooksApproved());
            return "myBasket";
    }


    @GetMapping({"/{some}","/edit/{some}","/basket/{some}"})
    public String notFound(@PathVariable String some){
        throw new PageNotFoundException();
    }


}
