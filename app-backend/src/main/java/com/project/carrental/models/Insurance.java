package com.project.carrental.models;

import javax.persistence.Entity;

@Entity
public class Insurance {
    private String insuranceCode;

    private String insuranceName;

    private Double costPerDay;

    private ApplicationUser applicationUser;
}
