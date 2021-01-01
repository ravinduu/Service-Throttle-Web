package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.exceptions.VehicleEngineDosentExist;
import com.servicethrottle.servicethrottlebackend.exceptions.VehicleMakeDosentExist;
import com.servicethrottle.servicethrottlebackend.models.*;
import com.servicethrottle.servicethrottlebackend.models.dto.*;
import com.servicethrottle.servicethrottlebackend.repositories.VehicleModelDosentExist;
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
     * */

    private final VehicleService vehicleService;

    /**
     * add a new car under current customer
     *
     * only for CUSTOMER, SUPERVISOR and MOBILE MECHANIC
     * parameter CustomerVehicleDto is the information of the customer car
     * get currentCustomer logged in
     * */
    @PostMapping("customer-vehicle/add")
    public CustomerVehicle addCustomerVehicle(CustomerVehicleDto customerVehicleDto){
        return vehicleService.addCustomerVehicle(customerVehicleDto);
    }

    /**
     * get all customer vehicles
     *
     * only for ADMIN
     * */
    @GetMapping("customer-vehicle")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CustomerVehicle> getAllCustomerVehicles(){
        return vehicleService.getAllCustomerVehicles();
    }

    /**
     * get all customer vehicles
     *
     * only for ADMIN
     * */
    @GetMapping("customer-vehicle/{id}")
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
    @GetMapping("customer-vehicle/by-user/{username}")
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
    @GetMapping("customer-vehicle/my-vehicles")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public List<CustomerVehicle> getMyCustomerVehicles(){
        return vehicleService.getMyCustomerVehicles();
    }

    @GetMapping("customer-vehicle/my-vehicles/{id}")
    public CustomerVehicle getMyVehicle(@PathVariable long id){
        return vehicleService.getMyVehicle(id);
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
    @PutMapping("mobile-service-vehicle/{id}/add-mobile-mechanic")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MobileServiceVehicle addMechanicToMobileServiceVehicle(@PathVariable long id,String mobileMechanicUsername){
        return vehicleService.addMechanicToMobileServiceVehicle(id, mobileMechanicUsername);
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
    @PutMapping("mobile-service-vehicle/{id}/change-mobile-mechanic")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public MobileServiceVehicle changeMechanicOfMobileServiceVehicle(@PathVariable long id,String mobileMechanicUsername){
        return vehicleService.changeMechanicOfMobileServiceVehicle(id, mobileMechanicUsername);
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
    public VehicleMake getVehicleMake(@PathVariable long id) throws VehicleMakeDosentExist {
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
    public VehicleMake updateVehicleMake(@PathVariable long id, VehicleMakeDto vehicleMakeDto) throws VehicleMakeDosentExist {
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
    public int deleteVehicleMake(@PathVariable long id) throws VehicleMakeDosentExist {
        return vehicleService.deleteVehicleMake(id);
    }


    /**
     * add a new vehicle model
     *
     * only access by ADMIN
     * parameter {@link VehicleModelDto} is information about vehicle make and model
     * return {@link VehicleModel}
     * */
    @PutMapping("model/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public VehicleModel addVehicleModel(VehicleModelDto vehicleModelDto){
        return vehicleService.addVehicleModel(vehicleModelDto);
    }

    /**
     * get all available vehicle models as list
     *
     * */
    @GetMapping("model/all")
    public List<VehicleModel> getAllVehicleModel(){
        return vehicleService.getAllVehicleModel();
    }

    /**
     * get a available vehicle model by id
     *
     * parameter is the id of the specific model
     * return {@link VehicleModel}
     * */
    @GetMapping("model/{id}")
    public VehicleModel getVehicleModel(@PathVariable long id) throws VehicleModelDosentExist {
        return vehicleService. getVehicleModel(id);
    }

    /**
     * get all available vehicle models by vehicle make
     *
     *  parameter makeId is the id of specific vehicle make
     *  return {@link VehicleModel}
     *
     * */
    @GetMapping("model/by-make/{makeId}")
    public List<VehicleModel> getAllVehicleModelByMake(@PathVariable long makeId) throws VehicleModelDosentExist, VehicleMakeDosentExist {
        return vehicleService.getAllVehicleModelByMake(makeId);

    }

    /**
     *  update a vehicle model by its id
     *
     *  only for ADMIN
     *  parameters id is the modelId of which wants to edit, {@link VehicleModelDto} are the new info about model
     *  return updated {@link VehicleModel}
     *
     * */
    @PutMapping("model/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public VehicleModel updateVehicleModel(@PathVariable long id, VehicleModelDto vehicleModelDto) throws VehicleModelDosentExist {
        return vehicleService.updateVehicleModel(id, vehicleModelDto);
    }

    /**
     * delete a vehicle model by its id
     *
     *  only for ADMIN
     *  parameters id is the modelId of which wants to delete
     *  return 1 if the process is success or 0 if error
     *
     * */
    @DeleteMapping("model/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public int deleteVehicleModel(@PathVariable long id) throws VehicleModelDosentExist {
        return vehicleService.deleteVehicleModel(id);
    }

    /**
     * add a new vehicle engine type
     *
     * only access by ADMIN
     * parameter {@link VehicleEngineDto} is information about vehicle make and model
     * return {@link VehicleEngine}
     * */
    @PutMapping("engine/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public VehicleEngine addVehicleEngine(VehicleEngineDto vehicleEngineDto){
        return vehicleService.addVehicleEngine(vehicleEngineDto);
    }

    /**
     * get all available vehicle engines as list
     *
     * */
    @GetMapping("engine/all")
    public List<VehicleEngine> getAllVehicleEngine(){
        return vehicleService.getAllVehicleEngine();
    }

    /**
     * get a available vehicle engine by id
     *
     * parameter is the id of the specific model
     * return {@link VehicleEngine}
     * */
    @GetMapping("engine/{id}")
    public VehicleEngine getVehicleEngine(@PathVariable long id) throws VehicleEngineDosentExist {
        return vehicleService.getVehicleEngine(id);
    }


    /**
     *  update a vehicle engine type by its id
     *
     *  only for ADMIN
     *  parameters id is the modelId of which wants to edit, {@link VehicleEngineDto} are the new info about model
     *  return updated {@link VehicleEngine}
     *
     * */
    @PutMapping("engine/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public VehicleEngine updateVehicleEngine(@PathVariable long id, VehicleEngineDto vehicleEngineDto) throws VehicleEngineDosentExist {
        return vehicleService.updateVehicleEngine(id, vehicleEngineDto);
    }

    /**
     * delete a vehicle engine type by its id
     *
     *  only for ADMIN
     *  parameters id is the EngineId of which wants to delete
     *  return 1 if the process is success or 0 if error
     *
     * */
    @DeleteMapping("engine/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public int deleteVehicleEngine(@PathVariable long id) throws VehicleEngineDosentExist {
        return vehicleService.deleteVehicleEngine(id);
    }

}
