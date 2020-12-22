package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "st_customer_vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVehicle extends Vehicle {
    @ManyToOne
    @JoinColumn(name = "st_customer_id", nullable = false)
    private Customer customer;

}
