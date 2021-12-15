package com.project.carrental.controllers;

import com.project.carrental.exceptions.ResourceNotFoundException;
import com.project.carrental.models.ApplicationUser;
import com.project.carrental.models.Car;
import com.project.carrental.repositories.CarsRepository;
import com.project.carrental.services.CarsService;
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

    @PostMapping("/add")
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        carsService.createCar(car);

        return new ResponseEntity<>(Car.class, HttpStatus.OK);
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

    @GetMapping("/{id}")
    public String getCarById(@PathVariable(name = "id") long carId, Model model) {
        Car car = carsService.getCarById(carId);

        model.addAttribute("car", car);

        //return ResponseEntity.ok().body(car);
        return "user/car-details-page";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(name = "id") long carId, @RequestBody Car newCar) throws ResourceNotFoundException {
        Car car = carsService.updateCar(carId, newCar);

        return ResponseEntity.ok().body(car);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.POST})
    public String deleteCar(@PathVariable(name = "id") long carId) throws ResourceNotFoundException {
        carsService.deleteCar(carId);

        return "redirect:/cars/all";
    }
}
