package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;

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

    private Year year;

    @ManyToOne
    @JoinColumn(name = "vehicle_make_id", nullable = false)
    private VehicleMake vehicleMake;

    @ManyToOne
    @JoinColumn(name = "vehicle_model_id", nullable = false)
    private VehicleModel vehicleModel;

    @ManyToOne
    @JoinColumn(name = "vehicle_engine_id", nullable = false)
    private VehicleEngine vehicleEngine;

}
