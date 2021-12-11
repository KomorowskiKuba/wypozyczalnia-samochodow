package com.project.carrental.services;

import com.project.carrental.exceptions.ResourceNotFoundException;
import com.project.carrental.models.Car;
import com.project.carrental.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarsService {
    @Autowired
    private CarsRepository carsRepository;

    public void createCar(Car car) {
        carsRepository.save(car);
    }

    public List<Car> getAllCars() {
        carsRepository.save(new Car(1, "Mercedes", "CLS 200", "Limousine", 5.0, 40, 300));
        carsRepository.save(new Car(2, "BMW", "e 43", "Limousine", 5.0, 40, 300));
        carsRepository.save(new Car(3, "Skoda", "Fabia", "Limousine", 5.0, 40, 300));
        carsRepository.save(new Car(4, "Skoda", "Octavia", "Limousine", 5.0, 40, 300));
        return carsRepository.findAll();
    }

    public Car getCarById(long carId) {
        return carsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + carId));
    }

    public Car updateCar(long carId, Car newCar) {
        Car car = carsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + carId));

        car.setBrand(newCar.getBrand());
        car.setModel(newCar.getModel());
        car.setType(newCar.getType());
        car.setEngineCapacity(newCar.getEngineCapacity());
        car.setPricePerHour(newCar.getPricePerHour());
        car.setPricePerDay(newCar.getPricePerDay()); //TODO: Change this

        carsRepository.save(car);

        return car;
    }

    public void deleteCar(long carId) {
        carsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + carId));
        carsRepository.deleteById(carId);
    }
}