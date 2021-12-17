package com.project.carrental.repositories;

import com.project.carrental.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandAndModel(String brand, String model);
}