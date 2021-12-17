package com.project.carrental.controllers;

import com.google.gson.Gson;
import com.project.carrental.exceptions.ResourceNotFoundException;
import com.project.carrental.models.ApplicationUser;
import com.project.carrental.models.Car;
import com.project.carrental.repositories.CarsRepository;
import com.project.carrental.services.CarsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarsApiController {
    @Autowired
    private CarsService carsService;

    @GetMapping("/add")
    public String createCarForm(Model model) {
        Car car = new Car();

        model.addAttribute("car", car);

        return "admin/create-page";
    }

    @PostMapping("/add")
    public String createCar(Car car) {
        carsService.createCar(car);

        return "redirect:/cars/all";
    }

    @RequestMapping("/all")
    public String getAllCars(Model model) {
        List<Car> cars =  carsService.getAllCars();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = ((ApplicationUser)principal).getRole();

        model.addAttribute("cars", cars);

        if (role == "ROLE_ADMIN") {
            return "admin/admin-home-page";
        } else {
            return "user/user-home-page";
        }
    }

    @GetMapping("/car/{id}")
    public String getCar(@PathVariable(name = "id") long carId) {

        System.out.println(carsService.getCarById(carId).getModel());
        Gson gson = new Gson();
        return gson.toJson(carsService.getCarById(carId));
    }

    @GetMapping("/{id}")
    public String getCarById(@PathVariable(name = "id") long carId, Model model) {
        Car car = carsService.getCarById(carId);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = ((ApplicationUser)principal).getRole();

        model.addAttribute("car", car);

        System.out.println(car.getId() + " " + car.isRented());

        if (role == "ROLE_ADMIN") {
            model.addAttribute("insurance", car.getInsurance());

            return "admin/car-details-page-admin";
        } else {
            return "user/car-details-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String getUpdateCarForm(@PathVariable(name = "id") long carId, Model model) {
        Car car = carsService.getCarById(carId);

        model.addAttribute("car", car);

        return "admin/edit-page";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String submitUpdateCarForm(Car newCar) throws ResourceNotFoundException {
        carsService.updateCar(newCar.getId(), newCar);

        return "redirect:/cars/all";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteCar(@PathVariable(name = "id") long carId) throws ResourceNotFoundException {
        carsService.deleteCar(carId);

        return "redirect:/cars/all";
    }
}
