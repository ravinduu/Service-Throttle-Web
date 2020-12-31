package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.CustomerVehicle;
import com.servicethrottle.servicethrottlebackend.models.MobileServiceVehicle;
import com.servicethrottle.servicethrottlebackend.models.VehicleMake;
import com.servicethrottle.servicethrottlebackend.models.dto.CustomerVehicleDto;
import com.servicethrottle.servicethrottlebackend.models.dto.MobileServiceVehicleDto;
import com.servicethrottle.servicethrottlebackend.models.dto.VehicleMakeDto;
import com.servicethrottle.servicethrottlebackend.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/st/vehicle")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class VehicleController {

    /**
     * TO-DO
     * implement remove cust vehicle
     * change methods use id as parameter to path var
     * edit api use "_" to "-"
     * CRUD vehicle model, engine
     * */

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


    /**
     * add a new service vehicle for company
     *
     * only access by ADMIN
     * parameter MobileServiceVehicleDto is the information of the service vehicle
     * */
    @PostMapping("mobile-service-vehicle/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MobileServiceVehicle addMobileServiceVehicle(MobileServiceVehicleDto mobileServiceVehicleDto){
        return vehicleService.addMobileServiceVehicle(mobileServiceVehicleDto);
    }

    /**
     * add mechanic to a service vehicle of company
     *
     * only access by ADMIN
     *
     * */
    @PutMapping("mobile-service-vehicle/add-mobile-mechanic")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MobileServiceVehicle addMechanicToMobileServiceVehicle(long mobileServiceVehicleId,String mobileMechanicUsername){
        return vehicleService.addMechanicToMobileServiceVehicle(mobileServiceVehicleId, mobileMechanicUsername);
    }

    /**
     * remove mechanic from a service vehicle of company
     *
     * only access by ADMIN
     *
     * */
    @PutMapping("mobile-service-vehicle/remove-mobile-mechanic/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MobileServiceVehicle removeMechanicOfMobileServiceVehicle(@PathVariable long id){
        return vehicleService.removeMechanicOfMobileServiceVehicle(id);
    }

    /**
     * change mechanic from service vehicle of company
     *
     * only access by ADMIN
     *
     * */
    @PutMapping("mobile-service-vehicle/change-mobile-mechanic")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MobileServiceVehicle changeMechanicOfMobileServiceVehicle(long mobileServiceVehicleId,String mobileMechanicUsername){
        return vehicleService.changeMechanicOfMobileServiceVehicle(mobileServiceVehicleId, mobileMechanicUsername);
    }

    /**
     * update details of a service vehicle of company
     *
     * only access by ADMIN
     * MobileServiceVehicleDto is the new data of the service vehicle
     * */
    @PutMapping("mobile-service-vehicle/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MobileServiceVehicle updateMobileServiceVehicle(MobileServiceVehicleDto mobileServiceVehicleDto){
        return vehicleService.updateMobileServiceVehicle(mobileServiceVehicleDto);
    }

    /**
     * get service vehicle of company by id
     *
     * only access by ADMIN
     * */
    @GetMapping("mobile-service-vehicle/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MobileServiceVehicle getMobileServiceVehicle(@PathVariable long id){
        return vehicleService.getMobileServiceVehicle(id);
    }

    /**
     * get all service vehicles of company
     *
     * only access by ADMIN
     * */
    @GetMapping("mobile-service-vehicle/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<MobileServiceVehicle> getAllMobileServiceVehicle(){
        return vehicleService.getAllMobileServiceVehicle();
    }

    /**
     * remove a service vehicle of company
     *
     * only access by ADMIN
     * */
    @DeleteMapping("mobile-service-vehicle/remove/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public int removeMobileServiceVehicle(@PathVariable long id){
        return vehicleService.removeMobileServiceVehicle(id);
    }

    /**
     * add vehicle make
     *
     * only access by ADMIN
     * */
    @PostMapping("make/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public VehicleMake addVehicleMake(VehicleMakeDto vehicleMakeDto){
        return vehicleService.addVehicleMake(vehicleMakeDto);
    }

    /**
     * get all vehicle make
     *
     * only access by ADMIN
     * */
    @GetMapping("make/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<VehicleMake> getAllVehicleMake(){
        return vehicleService.getAllVehicleMake();
    }

    /**
     * get a vehicle make  by id
     *
     * only access by ADMIN
     * return {@link VehicleMake} with matching id or will return an empty instance of {@link VehicleMake}
     * */
    @GetMapping("make/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public VehicleMake getVehicleMake(@PathVariable long id){
        return vehicleService.getVehicleMake(id);
    }

    /**
     * update a vehicle make
     *
     * only access by ADMIN
     * {@link VehicleMakeDto} is new data of the vehicle make
     * return updated {@link VehicleMake}
     * */
    @PutMapping("make/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public VehicleMake updateVehicleMake(@PathVariable long id, VehicleMakeDto vehicleMakeDto){
        return vehicleService.updateVehicleMake(id, vehicleMakeDto);
    }

    /**
     * remove a vehicle make
     *
     * only access by ADMIN
     * parameter PathVariable id is the vehicle make id that want to remove
     * return 1 if the process is success or 0 if error
     * */
    @DeleteMapping("make/remove/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public int deleteVehicleMake(@PathVariable long id){
        return vehicleService.deleteVehicleMake(id);
    }
}
