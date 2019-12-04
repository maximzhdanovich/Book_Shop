package com.test.controller;

import com.test.db.model.CustomUserDetail;
import com.test.db.model.User;
import com.test.service.BasketService;
import com.test.service.UserService;
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
        model.addAttribute("user", userService.getCurrentUser(customUserDetail));
        return "accountEdit";
    }

    @PostMapping("/edit")
    public String accountEdit(@AuthenticationPrincipal CustomUserDetail customUserDetail,
                              @Valid User user,
                              BindingResult bindingResult,
                              @RequestParam("password1") String password1,
                              @RequestParam("password2") String password2,
                              Model model) {
        User currentUser = userService.getCurrentUser(customUserDetail);
        boolean empty1 = StringUtils.isEmpty(password1);
        boolean empty2 = StringUtils.isEmpty(password2);
        boolean equalsNewPassword = password1.equals(password2);
        boolean equalsOldPassword = user.getPassword().equals(currentUser.getPassword());
        if (!equalsOldPassword)
        {
            model.addAttribute("passwordError","Old password error");
        }
        if (empty1) {
            model.addAttribute("password1Error","new password can't be empty");
        }
        if (empty2) {
            model.addAttribute("password2Error","repeat password can't be empty");
        }
        if (!password1.equals(password2)) {
            model.addAttribute("password1Error", "Password are different");
            model.addAttribute("password2Error", "Password are different");
        }
        if (empty1 || empty2 ||!equalsOldPassword|| bindingResult.hasErrors()|| !equalsNewPassword) {
            Collector<FieldError, ?, Map<String, String>> fieldErrorMapCollector = Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            );

            Map<String, String> collectErrors = bindingResult.getFieldErrors().stream().collect(fieldErrorMapCollector);
            model.mergeAttributes(collectErrors);
            model.addAttribute("user",currentUser);
            return "accountEdit";
        }
        userService.update(currentUser,user.getUsername(), password1,user.getEmail());

        return "redirect:/account";
    }

    @GetMapping("/basket")
    public String myBasket(@AuthenticationPrincipal CustomUserDetail customUserDetail, Model model) {
        model.addAttribute("user", userService.getCurrentUser(customUserDetail));
        if(basketService.getByUser(userService.getCurrentUser(customUserDetail)).getBooks()!=null){
        model.addAttribute("books", basketService.getByUser(userService.getCurrentUser(customUserDetail)).getBooks());}
        return "myBasket";
    }
}
