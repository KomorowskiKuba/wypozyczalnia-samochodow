package com.project.carrental.services;

import com.project.carrental.exceptions.ResourceNotFoundException;
import com.project.carrental.models.BillingDetails;
import com.project.carrental.models.Car;
import com.project.carrental.repositories.BillingDetailsRepository;
import com.project.carrental.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingDetailsService {

    @Autowired
    private BillingDetailsRepository billingDetailsRepository;

    public void createBillingDetails(BillingDetails details) {
        billingDetailsRepository.save(details);
    }

    public BillingDetails updateBillingDetails(long id, BillingDetails newBillingDetails) {
        BillingDetails billingDetails = billingDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Billing details not found: " + id));
        System.out.println(id);
        billingDetails.setCardNumber(newBillingDetails.getCardNumber());
        billingDetails.setCardCVVCode(newBillingDetails.getCardCVVCode());
        billingDetails.setExpirationDate(newBillingDetails.getExpirationDate());

        billingDetailsRepository.save(billingDetails);

        return billingDetails;
    }
}
