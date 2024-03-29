package com.project.carrental.controllers;

import com.google.gson.Gson;
import com.project.carrental.models.ApplicationUser;
import com.project.carrental.models.Car;
import com.project.carrental.models.Rental;
import com.project.carrental.services.CarsService;
import com.project.carrental.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Controller
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @Autowired
    private CarsService carsService;

    public RentalController(RentalService rentalService, CarsService carsService) {
        this.rentalService = rentalService;
        this.carsService = carsService;
    }

    @GetMapping("/rental/all")
    public String getAllRentals(Model model) {
        List<Rental> rentalList = rentalService.getAllRentals();

        if (rentalService.getAllRentals().size() == 0) {
            rentalList.add(new Rental(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
        }

        model.addAttribute("rentals", rentalList);

        return "admin/rentals-page";
    }

    @GetMapping("/rental/new/{id}")
    public String getCreateRentalForm(@PathVariable(name = "id") Long id, Model model) {
        Rental rental = new Rental();


        model.addAttribute("rental", rental);
        model.addAttribute("id", id.intValue());

        return "user/rental-page";
    }

    @RequestMapping(value = "/rental/new/{id}", method = RequestMethod.POST)
    public String createRental(@PathVariable(name = "id") Long id, Rental rental) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser user = (ApplicationUser)principal;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("user", "pass");
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<Car> response = new RestTemplate().exchange("http://localhost:8080/cars/car/" + id, HttpMethod.GET, request, Car.class);
        System.out.println(response.getBody().getModel());

        //System.out.println(car.getModel() + " " + car.getId());

        //ental.setApplicationUser(user);
        //rental.setCar(car);

        //HttpHeaders headers = new HttpHeaders();
        //headers.setBasicAuth("user", "pass");
        //HttpEntity request = new HttpEntity(headers);

        //ResponseEntity<Car> response = new RestTemplate().exchange("http://localhost:8080/cars/changeavailability/" + car.getId(), HttpMethod.POST, request, Car.class);

        rentalService.createRental(rental);

        return "redirect:/cars/all";
    }
}
