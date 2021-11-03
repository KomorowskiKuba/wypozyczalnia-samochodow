package com.project.carrental.repositories;

import com.project.carrental.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Car, Long> {}