package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "st_customer")
public class Customer extends User {
    private int loyaltyPoints = 0;
}
