package com.project.carrental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalBeginningDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rentalEndDate;

    //private Location pickUpLocation;

    @ManyToOne
    @JoinColumn(name = "applicationUser_id")
    private ApplicationUser applicationUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    public Rental(Date rentalBeginningDate, Date rentalEndDate) {
        this.rentalBeginningDate = rentalBeginningDate;
        this.rentalEndDate = rentalEndDate;
    }
}
