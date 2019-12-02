package com.test.controller;

import com.test.db.model.User;
import com.test.service.RoleService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('ADMIN')")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users",userService.findAll());
        return "userList";
    }
    @GetMapping("{user}")
    public String userEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam("userId") User user,
                           @RequestParam String role,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email){
        userService.update(user,role,username,password,email);
        return "redirect:/user";
    }

    @GetMapping("{user}/basket")
    public String showUserBasket(@PathVariable User user,Model model){
        model.addAttribute("books",user.getBasket().getBooks());
        return "myBasket";
    }

}
