package com.servicethrottle.servicethrottlebackend.models;

import com.servicethrottle.servicethrottlebackend.models.enums.Engine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 Class for vehicle engine type
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_engines")
public class VehicleEngine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String engine;
}
