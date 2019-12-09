package com.bookshop.controller;

import com.bookshop.model.entity.User;
import com.bookshop.service.BasketService;
import com.bookshop.service.RoleService;
import com.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    public String addUser(@RequestParam("password1") String password1,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        boolean empty = StringUtils.isEmpty(password1);
        if (empty) {
            model.addAttribute("password1Error", "error");
        }
        if (!StringUtils.isEmpty(user.getPassword()) && !password1.equals(user.getPassword())) {
            model.addAttribute("passwordError", "Password are different");
        }
        Optional<User> userFromDb = userService.findByUsername(user.getUsername());
        if (userFromDb.isPresent()) {
            model.addAttribute("usernameError", "User exists!");
        }
        if (empty || bindingResult.hasErrors() || userFromDb.isPresent()) {
            Collector<FieldError, ?, Map<String, String>> fieldErrorMapCollector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );

            Map<String, String> collectErrors = bindingResult.getFieldErrors().stream().collect(fieldErrorMapCollector);
            model.mergeAttributes(collectErrors);
            return "registration";
        }

        userService.create(user);
        return "successRegistration";
    }
}