package com.project.carrental.controllers;

import com.project.carrental.exceptions.ResourceNotFoundException;
import com.project.carrental.models.Car;
import com.project.carrental.repositories.CarsRepository;
import com.project.carrental.services.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carsapi")
public class CarsApiController {
    @Autowired
    private CarsService carsService;

    @PostMapping("/cars")
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        carsService.createCar(car);

        return new ResponseEntity<>(Car.class, HttpStatus.OK);
    }

    @RequestMapping("/cars")
    public String getAllCars(Model model) {
        List<Car> cars =  carsService.getAllCars();

        model.addAttribute("cars", cars);

        return "home-page";
    }

    @GetMapping("cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(name = "id") long carId) {
        Car car = carsService.getCarById(carId);

        return ResponseEntity.ok().body(car);
    }

    @PutMapping("cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(name = "id") long carId, @RequestBody Car newCar) throws ResourceNotFoundException {
        Car car = carsService.updateCar(carId, newCar);

        return ResponseEntity.ok().body(car);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(name = "id") long carId) throws ResourceNotFoundException {
        carsService.deleteCar(carId);

        return ResponseEntity.ok().build();
    }
}
