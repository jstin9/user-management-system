package com.jstn9.usermanagementsystem.controllers;

import com.jstn9.usermanagementsystem.dto.UserRegistrationDto;
import com.jstn9.usermanagementsystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userDto") @Valid UserRegistrationDto userDto,
                                      BindingResult result,
                                      Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        userService.createUser(userDto);
        return "redirect:/register?success";
    }
}
