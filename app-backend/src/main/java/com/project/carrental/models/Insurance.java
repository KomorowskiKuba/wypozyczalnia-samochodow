package com.project.carrental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String insuranceCode;

    private String insuranceName;

    private Double costPerDay;

    @OneToOne(mappedBy = "insurance", fetch = FetchType.EAGER)
    private Car car;

    public Insurance(String insuranceCode, String insuranceName, Double costPerDay) {
        this.insuranceCode = insuranceCode;
        this.insuranceName = insuranceName;
        this.costPerDay = costPerDay;
    }
}
