package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "st_mobile_service_vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileServiceVehicle extends Vehicle {

    private long capacity;
}
