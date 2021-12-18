package com.project.carrental.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Rental {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalBeginningDate;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalEndDate;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "applicationUser_id")
    private ApplicationUser applicationUser;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    public Rental(Date rentalBeginningDate, Date rentalEndDate) {
        this.rentalBeginningDate = rentalBeginningDate;
        this.rentalEndDate = rentalEndDate;
    }
}
