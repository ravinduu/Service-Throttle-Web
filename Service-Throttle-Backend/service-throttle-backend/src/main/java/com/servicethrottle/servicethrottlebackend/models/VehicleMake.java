package com.servicethrottle.servicethrottlebackend.models;

import com.servicethrottle.servicethrottlebackend.models.enums.Make;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 Class for vehicle brand
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_makes")
public class VehicleMake implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 50)
    @Column(length = 50)
    private Make make;


}
