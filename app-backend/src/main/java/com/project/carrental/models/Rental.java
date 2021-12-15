package com.project.carrental.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Rental {

    @Id
    private int rentalId;

    private Date rentalBeginningDate;

    private Date rentalEndDate;

    //private Location pickUpLocation;

    private ApplicationUser applicationUser;
}
