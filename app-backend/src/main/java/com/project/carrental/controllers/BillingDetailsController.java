package com.project.carrental.controllers;

import com.project.carrental.exceptions.ResourceNotFoundException;
import com.project.carrental.models.BillingDetails;
import com.project.carrental.models.Car;
import com.project.carrental.services.BillingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/billing")
public class BillingDetailsController {
    @Autowired
    private BillingDetailsService billingDetailsService;

    @PostMapping("/add")
    public void createBillingDetails() {
        billingDetailsService.createBillingDetails(new BillingDetails());
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String submitUpdateCarForm(BillingDetails details) throws ResourceNotFoundException {
        System.out.println(details.toString());
        billingDetailsService.updateBillingDetails(details.getId(), details);

        return "redirect:/my-account";
    }
}
