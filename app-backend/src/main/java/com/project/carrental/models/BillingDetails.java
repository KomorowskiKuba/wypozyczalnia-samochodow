package com.project.carrental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "billingDetails")
public class BillingDetails implements Serializable {
    @Id
    private Long id;

    private String cardNumber;

    private String cardCVVCode;

    private String expirationDate;

    @OneToOne(mappedBy = "billingDetails")
    private ApplicationUser applicationUser;


    public BillingDetails(String cardNumber, String cardCVVCode, String expirationDate) {
        this.cardNumber = cardNumber;
        this.cardCVVCode = cardCVVCode;
        this.expirationDate = expirationDate;
    }
}
