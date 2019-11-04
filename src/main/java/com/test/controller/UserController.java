package com.test.controller;

import com.test.db.model.User;
import com.test.service.RoleService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@PreAuthorize("hasAnyRole('ADMIN')")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
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
//                           @RequestParam Map<String, String> role,
                           @RequestParam String role,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email){
        user.setUsername(username);
        user.setRole(roleService.findByTitle(role));
        user.setEmail(email);
        user.setPassword(password);
        userService.save(user);
        return "redirect:/user";
    }
}
