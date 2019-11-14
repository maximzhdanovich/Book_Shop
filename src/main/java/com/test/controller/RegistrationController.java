package com.test.controller;

import com.test.db.model.Basket;
import com.test.db.model.User;
import com.test.service.BasketService;
import com.test.service.RoleService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BasketService basketService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        Optional<User> userFromDb = userService.findByUsername(user.getUsername());
        if (userFromDb.isPresent()) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        userService.create(user);
        return "redirect:/login";
    }
}