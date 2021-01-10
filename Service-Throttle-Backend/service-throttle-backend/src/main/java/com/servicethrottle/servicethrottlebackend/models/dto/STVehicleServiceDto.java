package com.servicethrottle.servicethrottlebackend.models.dto;

import com.servicethrottle.servicethrottlebackend.models.enums.VehicleServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class STVehicleServiceDto {

    private long id;

    private String vehicleServiceName;

    private double vehicleServicePrice;

    private String vehicleServiceDescription;

    private VehicleServiceType vehicleServiceType;
}
