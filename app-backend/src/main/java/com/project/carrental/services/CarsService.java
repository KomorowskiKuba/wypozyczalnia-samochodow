package com.project.carrental.services;

import com.project.carrental.exceptions.ResourceNotFoundException;
import com.project.carrental.models.Car;
import com.project.carrental.models.Insurance;
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
        if (carsRepository.findAll().size() == 0) {
            carsRepository.save(new Car(1, "Mercedes", "CLS 200", "Limousine", 5.0, 40, 300, new Insurance(1L, "XD1"), false));
            carsRepository.save(new Car(2, "BMW", "e 43", "Limousine", 5.0, 40, 300, new Insurance(2L, "XD2"), false));
            carsRepository.save(new Car(3, "Skoda", "Fabia", "Limousine", 5.0, 40, 300, new Insurance(3L, "XD3"), false));
            carsRepository.save(new Car(4, "Skoda", "Octavia", "Limousine", 5.0, 40, 300, new Insurance(4L, "XD4"), false));
        }

        return carsRepository.findAll();
    }

    public List<Car> getAllByBrandAndModel(String brand, String model) {
        return carsRepository.findByBrandAndModel(brand, model);
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

    public void changeCarsAvailability(long carId, boolean isRented) {
        Car car = carsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + carId));
        car.setRented(isRented);

        carsRepository.save(car);
    }
}
