package com.project.carrental.models;

import lombok.*;

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

    @NonNull
    private String insuranceCode;

    @NonNull
    private String insuranceName;

    @NonNull
    private Double costPerDay;

    @OneToOne(mappedBy = "insurance", fetch = FetchType.EAGER)
    private Car car;

    public Insurance(String insuranceCode, String insuranceName, Double costPerDay) {
        this.insuranceCode = insuranceCode;
        this.insuranceName = insuranceName;
        this.costPerDay = costPerDay;
    }
}
