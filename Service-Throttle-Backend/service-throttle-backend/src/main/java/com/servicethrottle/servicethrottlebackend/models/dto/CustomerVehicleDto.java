package com.servicethrottle.servicethrottlebackend.models.dto;

import com.servicethrottle.servicethrottlebackend.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVehicleDto {

    private Year year;

    private VehicleMake vehicleMake;

    private VehicleModel vehicleModel;

    private VehicleEngine vehicleEngine;

}
