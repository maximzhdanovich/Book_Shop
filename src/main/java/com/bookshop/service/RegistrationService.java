package com.bookshop.service;

import com.bookshop.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    public String addNewUser(String repeatPassword, @Valid User newUser, BindingResult bindingResult, Model model) {
        boolean empty = StringUtils.isEmpty(repeatPassword);
        if (empty) {
            model.addAttribute("password1EmptyError", "Repeat Password can not be empty");
        }
        if (!StringUtils.isEmpty(newUser.getPassword()) && !repeatPassword.equals(newUser.getPassword())) {
            model.addAttribute("passwordDifferentError", "Password are different");
        }
        Optional<User> userFromDb = userService.findByUsername(newUser.getUsername());
        if (userFromDb.isPresent()) {
            model.addAttribute("usernameExistsError", "User exists!");
        }
        userFromDb = userService.findByEmail(newUser.getEmail());
        if (userFromDb.isPresent()) {
            model.addAttribute("emailExistsError", "Email is already in use");
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

        userService.create(newUser);
        basketService.create(newUser);
        return "successRegistration";
    }
}
