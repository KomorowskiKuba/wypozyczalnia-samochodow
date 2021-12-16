package com.project.carrental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    private Long id;

    private String insuranceCode;

    private String insuranceName;

    private Double costPerDay;

    @OneToOne(mappedBy = "insurance")
    private Car car;

    public Insurance(Long id, String insuranceCode) {
        this.id = id;
        this.insuranceCode = insuranceCode;
    }
}
