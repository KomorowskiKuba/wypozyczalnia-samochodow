package com.project.carrental.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BillingDetails {

    @Id
    private String cardNumber;

    private String cardCVVCode;

    private String expirationDate;
}
