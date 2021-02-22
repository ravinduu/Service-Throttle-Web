package com.servicethrottle.servicethrottlebackend.models.dto;

import com.servicethrottle.servicethrottlebackend.models.VehicleEngine;
import com.servicethrottle.servicethrottlebackend.models.VehicleMake;
import com.servicethrottle.servicethrottlebackend.models.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileServiceVehicleDto {

    private Year year;

    private VehicleMake vehicleMake;

    private VehicleModel vehicleModel;

    private VehicleEngine vehicleEngine;

    private double capacity;
}
