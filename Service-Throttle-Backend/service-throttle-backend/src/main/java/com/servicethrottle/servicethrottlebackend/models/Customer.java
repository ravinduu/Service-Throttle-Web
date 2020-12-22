package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "st_customer")
public class Customer extends User {
    private int loyaltyPoints = 0;

    @OneToMany
    private Set<CustomerVehicle> customerVehicleSet;
}
