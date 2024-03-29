package com.project.carrental.controllers;

import com.project.carrental.models.ApplicationUser;
import com.project.carrental.models.BillingDetails;
import com.project.carrental.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login-user")
    public String login(Model model) {
        return "general/login";
    }

    @GetMapping("/sign-up-user")
    public String signUp(Model model) {
        model.addAttribute("user", new ApplicationUser());

        return "general/sign-up";
    }

    @PostMapping("/register-user")
    public String register(ApplicationUser applicationUser) {
        applicationUser.setBillingDetails(new BillingDetails());
        userService.addUser(applicationUser);

        return "general/login";
    }

    @GetMapping("/my-account")
    public String myAccount(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser user = (ApplicationUser) auth.getPrincipal();

        model.addAttribute("billingDetails", user.getBillingDetails());
        model.addAttribute("user", auth.getPrincipal());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = ((ApplicationUser)principal).getRole();

        if (role == "ROLE_ADMIN") {
            return "admin/admin-account-page";
        } else {
            return "user/user-account-page";
        }
    }

    @GetMapping("/about")
    public String about() {
        return "general/about-page";
    }
}
