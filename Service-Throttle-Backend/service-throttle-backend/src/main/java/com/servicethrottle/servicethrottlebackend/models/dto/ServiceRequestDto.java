package com.servicethrottle.servicethrottlebackend.models.dto;

import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.CustomerVehicle;
import com.servicethrottle.servicethrottlebackend.models.Location;
import com.servicethrottle.servicethrottlebackend.models.STVehicleService;
import com.servicethrottle.servicethrottlebackend.models.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequestDto {
    private long id;

    private CustomerVehicle customerVehicle;

    private Set<STVehicleService> vehicleServices = new HashSet<>();

    private Location location;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

}
