package com.project.carrental.models;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String brand;

    @NonNull
    private String model;

    @NonNull
    private String type; //TODO: Change to enum

    @NonNull
    private double engineCapacity;

    @NonNull
    private double pricePerHour;

    @NonNull
    private double pricePerDay;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_id", referencedColumnName = "id")
    private Insurance insurance;

    private boolean isRented = false;

    //@OneToOne(mappedBy = "car")
    //private Rental rental;
}
