package com.servicethrottle.servicethrottlebackend.models;

import com.servicethrottle.servicethrottlebackend.models.enums.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

/**
 Class for vehicle model
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_models")
public class VehicleModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_make_id", nullable = false)
    private VehicleMake vehicleMake;

    private String model;
}
