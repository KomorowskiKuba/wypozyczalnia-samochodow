package com.project.carrental.models;

import javax.persistence.Entity;

@Entity
public class BillingDetails {
    private String cardNumber;

    private String cardCVVCode;

    private String expirationDate;
}
