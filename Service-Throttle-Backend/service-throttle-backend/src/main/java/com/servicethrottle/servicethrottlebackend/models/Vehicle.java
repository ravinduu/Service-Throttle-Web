package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

/**
 Vehicles
 */

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = LAZY)
    private VehicleMake vehicleMake;

    @OneToOne(fetch = LAZY)
    private VehicleModel vehicleModel;

    @OneToOne(fetch = LAZY)
    private VehicleEngine vehicleEngine;

}
