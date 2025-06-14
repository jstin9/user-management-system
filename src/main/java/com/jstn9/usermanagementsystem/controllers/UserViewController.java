package com.jstn9.usermanagementsystem.controllers;

import com.jstn9.usermanagementsystem.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    private final UserService userService;

    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String viewAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }
}

