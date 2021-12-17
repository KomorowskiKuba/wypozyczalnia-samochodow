package com.project.carrental.controllers;

import com.project.carrental.models.Car;
import com.project.carrental.services.RentalService;
import com.project.carrental.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RentalController {

    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    //@GetMapping("/login-user")
    //public String login(Model model) {
    //    return "general/login";
    //}

    @GetMapping("/rental/new")
    public String getUpdateCarForm() {
        return "user/rental-page";
    }
}
