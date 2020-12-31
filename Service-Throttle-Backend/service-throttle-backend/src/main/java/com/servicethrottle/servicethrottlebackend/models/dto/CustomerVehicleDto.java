package com.servicethrottle.servicethrottlebackend.models.dto;

import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.VehicleEngine;
import com.servicethrottle.servicethrottlebackend.models.VehicleMake;
import com.servicethrottle.servicethrottlebackend.models.VehicleModel;

import java.time.Year;

public class CustomerVehicleDto {

    private Year year;

    private VehicleMake vehicleMake;

    private VehicleModel vehicleModel;

    private VehicleEngine vehicleEngine;

    private Customer customer;
}
