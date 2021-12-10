package com.project.carrental.controllers;

import com.project.carrental.models.ApplicationUser;
import com.project.carrental.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home-page")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new ApplicationUser());
        return "general/sign-up";
    }

    @PostMapping("/register")
    public String register(ApplicationUser applicationUser) {
        userService.addUser(applicationUser);
        return "general/sign-up";
    }
}
