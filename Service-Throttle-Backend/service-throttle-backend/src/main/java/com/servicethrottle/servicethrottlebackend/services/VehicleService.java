package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.CustomerVehicle;
import com.servicethrottle.servicethrottlebackend.models.dto.CustomerVehicleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VehicleService {

    public CustomerVehicle addCustomerVehicle(CustomerVehicleDto customerVehicleDto){
        return new CustomerVehicle();
    }
}
