package com.bookshop.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("currentUser", userService.getCurrentUser(customUserDetail).get());
        return "accountEdit";
    }

    @PostMapping("/edit")
    public String accountEdit(@AuthenticationPrincipal CustomUserDetail customUserDetail,
                              @Valid User newUser,
                              BindingResult bindingResult,
                              @RequestParam("password1") String password1,
                              @RequestParam("password2") String password2,
                              Model model) {
        User currentUser = userService.getCurrentUser(customUserDetail).get();
        boolean empty1 = StringUtils.isEmpty(password1);
        boolean empty2 = StringUtils.isEmpty(password2);
        boolean equalsNewPassword = password1.equals(password2);
        boolean equalsOldPassword = newUser.getPassword().equals(currentUser.getPassword());
        boolean present = userService.findByEmail(newUser.getEmail()).isPresent();
        if (!currentUser.getEmail().equals(newUser.getEmail()) && present)
            model.addAttribute("emailExistError", "Email is already in use");
        boolean present1 = userService.findByUsername(newUser.getUsername()).isPresent();
        if (!currentUser.getUsername().equals(newUser.getUsername()) && present1) {
            model.addAttribute("usernameExistError", "Username is already in use");
        }
        if (!equalsOldPassword) {
            model.addAttribute("passwordInputError", "Old password incorrect");
        }
        if (empty1) {
            model.addAttribute("password1EmptyError", "new password can't be empty");
        }
        if (empty2) {
            model.addAttribute("password2EmptyError", "repeat password can't be empty");
        }
        if (!password1.equals(password2)) {
            model.addAttribute("password1DifferentError", "Password are different");
            model.addAttribute("password2DifferentError", "Password are different");
        }
        if (empty1 || empty2 || !equalsOldPassword || bindingResult.hasErrors() || !equalsNewPassword
                || (!currentUser.getEmail().equals(newUser.getEmail()) && present) || (!currentUser.getUsername().equals(newUser.getUsername()) && present1)) {
            Collector<FieldError, ?, Map<String, String>> fieldErrorMapCollector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );

            Map<String, String> collectErrors = bindingResult.getFieldErrors().stream().collect(fieldErrorMapCollector);
            model.mergeAttributes(collectErrors);
            model.addAttribute("currentUser", currentUser);
            return "accountEdit";
        }
        userService.update(currentUser, newUser.getUsername(), password1, newUser.getEmail());

        return "redirect:/account";
    }

    @GetMapping("/basket")
    public String myBasket(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        if (userService.getCurrentUser(customUserDetail).isPresent()) {
            model.addAttribute("user", userService.getCurrentUser(customUserDetail));
            model.addAttribute("books", basketService.getByUser(userService.getCurrentUser(customUserDetail).get()).getBooks());
            model.addAttribute("approvedBooks", basketService.getByUser(userService.getCurrentUser(customUserDetail).get()).getBooksApproved());
            return "myBasket";
        } else return "redirect:/login";
    }
}
