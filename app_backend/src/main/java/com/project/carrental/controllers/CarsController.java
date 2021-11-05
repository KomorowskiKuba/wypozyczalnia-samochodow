package com.project.carrental.controllers;

import com.project.carrental.exceptions.ResourceNotFoundException;
import com.project.carrental.models.Car;
import com.project.carrental.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carsapi/v1")
public class CarsController {
    @Autowired
    private CarsRepository carsRepository;

    @PostMapping("/cars")
    public Car createCar(@RequestBody Car car) {
        return carsRepository.save(car);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carsRepository.findAll();
    }

    @GetMapping("cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(name = "id") long carId) {
        Car car = carsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + carId));

        return ResponseEntity.ok().body(car);
    }

    @PutMapping("cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(name = "id") long carId, @RequestBody Car newCar) throws ResourceNotFoundException {
        Car car = carsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + carId));

        car.setBrand(newCar.getBrand());
        car.setModel(newCar.getModel());
        car.setType(newCar.getType());
        car.setEngineCapacity(newCar.getEngineCapacity());
        car.setPricePerHour(newCar.getPricePerHour());
        car.setPricePerDay(newCar.getPricePerDay()); //TODO: Change this

        carsRepository.save(car);

        return ResponseEntity.ok().body(car);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(name = "id") long carId) throws ResourceNotFoundException {
        carsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + carId));
        carsRepository.deleteById(carId);

        return ResponseEntity.ok().build();
    }
}
