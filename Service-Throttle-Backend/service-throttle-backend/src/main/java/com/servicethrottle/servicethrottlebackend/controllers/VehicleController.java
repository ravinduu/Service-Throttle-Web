package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.CustomerVehicle;
import com.servicethrottle.servicethrottlebackend.models.dto.CustomerVehicleDto;
import com.servicethrottle.servicethrottlebackend.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/st")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * add a new car under current customer
     *
     * only for CUSTOMER, SUPERVISOR and MOBILE MECHANIC
     * parameter CustomerVehicleDto is the information of the customer car
     * get currentCustomer logged in
     * */
    @PostMapping("customer_vehicle/add")
    public CustomerVehicle addCustomerVehicle(CustomerVehicleDto customerVehicleDto){
        return vehicleService.addCustomerVehicle(customerVehicleDto);
    }

    /**
     * get all customer vehicles
     *
     * only for ADMIN
     * */
    @GetMapping("customer_vehicle")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CustomerVehicle> getAllCustomerVehicles(){
        return vehicleService.getAllCustomerVehicles();
    }

    /**
     * get all customer vehicles
     *
     * only for ADMIN
     * */
    @GetMapping("customer_vehicle/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CustomerVehicle getCustomerVehicle(@PathVariable Long id){
        return vehicleService.getCustomerVehicle(id);
    }

    /**
     * get all vehicles of a customer by username
     *
     * only for ADMIN
     * parameter is username of the customer
     * */
    @GetMapping("customer_vehicle/by_user/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CustomerVehicle> getCustomerVehicles(@PathVariable String username){
        return vehicleService.getCustomerVehicles(username);
    }

    /**
     * get all vehicles of a customer by username
     *
     * only for ADMIN
     * parameter is username of the customer
     * */
    @GetMapping("customer_vehicle/my_vehicles")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public List<CustomerVehicle> getMyCustomerVehicles(){
        return vehicleService.getMyCustomerVehicles();
    }

}
