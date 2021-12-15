package com.project.carrental.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Insurance {

    @Id
    private String insuranceCode;

    private String insuranceName;

    private Double costPerDay;

    private ApplicationUser applicationUser;
}
