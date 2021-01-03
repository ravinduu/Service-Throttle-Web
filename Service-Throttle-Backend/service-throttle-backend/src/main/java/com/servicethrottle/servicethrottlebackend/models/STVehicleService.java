package com.servicethrottle.servicethrottlebackend.models;

import com.servicethrottle.servicethrottlebackend.models.enums.VehicleServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * a service that provides by Service Throttle
 * */

@Entity
@Table(name = "st_vehicle_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class STVehicleService implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @NotNull
        private String vehicleServiceName;

        @NotNull
        private double vehicleServicePrice;

        private String vehicleServiceDescription;

        @NotNull
        private VehicleServiceType vehicleServiceType;

}
