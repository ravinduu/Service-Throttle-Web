package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.CustomerVehicle;
import com.servicethrottle.servicethrottlebackend.models.dto.CustomerVehicleDto;
import com.servicethrottle.servicethrottlebackend.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/st")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class VehicleController {

    private final VehicleService vehicleService;

    public CustomerVehicle addCustomerVehicle(CustomerVehicleDto customerVehicleDto){
        return vehicleService.addCustomerVehicle(customerVehicleDto);
    }
}
