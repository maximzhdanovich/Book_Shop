package com.test.controller;

import com.test.db.model.Basket;
import com.test.db.model.User;
import com.test.service.BasketService;
import com.test.service.RoleService;
import com.test.service.UserService;
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
            model.addAttribute("password1Error","error");
        }
        if (user.getPassword() != null && !password1.equals(user.getPassword())) {
            model.addAttribute("passwordError", "Password are different");
        }
        if (empty || bindingResult.hasErrors()) {
            Collector<FieldError, ?, Map<String, String>> fieldErrorMapCollector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );

            Map<String, String> collectErrors = bindingResult.getFieldErrors().stream().collect(fieldErrorMapCollector);
            model.mergeAttributes(collectErrors);
            return "registration";
        }
        Optional<User> userFromDb = userService.findByUsername(user.getUsername());
        if (userFromDb.isPresent()) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }
        userService.create(user);
        return "redirect:/login";
    }
}