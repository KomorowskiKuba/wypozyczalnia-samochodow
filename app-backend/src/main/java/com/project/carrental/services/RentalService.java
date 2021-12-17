package com.project.carrental.services;

import com.project.carrental.models.BillingDetails;
import com.project.carrental.models.Car;
import com.project.carrental.models.Insurance;
import com.project.carrental.models.Rental;
import com.project.carrental.repositories.BillingDetailsRepository;
import com.project.carrental.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public void createRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
}
