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
            carsRepository.save(new Car(1, "Mercedes", "CLS 200", "Limousine", 5.0, 100.0, 300.0, 5, 2019, "Biały", 250, new Insurance("1234", "PZU", 100.0) ,true));
            carsRepository.save(new Car(2, "BMW", "E43", "Limousine", 4.0, 80, 400.0, 5, 2018, "Czerwony", 180, new Insurance("1235", "PZU", 80.0) ,false));
            carsRepository.save(new Car(3, "Skoda", "Fabia", "Limousine", 2.0, 40, 200.0, 5, 2013, "Czerwony", 90, new Insurance("1236", "AXA", 40.0) ,false));
            carsRepository.save(new Car(4, "Skoda", "Octavia", "Limousine", 1.8, 30, 200.0, 5, 2009, "Czerwony", 75, new Insurance("1237", "AXA", 30.0) ,false));
            carsRepository.save(new Car(5, "Skoda", "Fabia", "Hatchback", 1.8, 30, 350.0, 5, 2009, "Zielony", 75, new Insurance("1237", "AXA", 30.0) ,false));

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
        car.setPricePerDay(newCar.getPricePerDay());

        carsRepository.save(car);

        return car;
    }

    public void deleteCar(long carId) {
        carsRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + carId));
        carsRepository.deleteById(carId);
    }

    public void changeCarsAvailability(long id) {
        Car car = carsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found:" + id));
        car.setRented(!car.isRented());

        carsRepository.save(car);
    }
}
