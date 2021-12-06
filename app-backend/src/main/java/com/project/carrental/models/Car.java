package com.project.carrental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
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
}
