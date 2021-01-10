package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.STVehicleService;
import com.servicethrottle.servicethrottlebackend.models.ServiceRequest;
import com.servicethrottle.servicethrottlebackend.models.dto.STVehicleServiceDto;
import com.servicethrottle.servicethrottlebackend.models.dto.ServiceRequestDto;
import com.servicethrottle.servicethrottlebackend.services.ServiceRequestService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/st/service-request")
@AllArgsConstructor
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    /**
     * create a new service request
     *
     * parameter {@link ServiceRequestDto} is the information about the service request
     * return {@link ServiceRequest}
     * */
    @PutMapping("/new")
    public ServiceRequest createServiceRequest(@RequestBody ServiceRequestDto serviceRequestDto){
        return serviceRequestService.createServiceRequest(serviceRequestDto);
    }

    /**
     * get all service requests
     *
     * only for ADMINS, SUPERVISORS, MOBILE MECHANIC
     * return List of {@link ServiceRequest} if available or empty List
     * */
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERVISOR','ROLE_MOBILEMECHANIC')")
    public List<ServiceRequest> getAllServiceRequests(){
        return serviceRequestService.getAllServiceRequests();
    }

    /**
     * get a service requests by id
     *
     * only for ADMINS, SUPERVISORS, MOBILE MECHANIC
     * PathVariable id is the id of the service request we want
     * return List of {@link ServiceRequest}
     * */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERVISOR','ROLE_MOBILEMECHANIC')")
    public ServiceRequest getAServiceRequest(@PathVariable long id){
        return serviceRequestService.getAServiceRequests(id);
    }

    /**
     * update a service requests
     *
     * only for CUSTOMERS
     * PathVariable id is the id of the service request we want and {@link ServiceRequestDto} is the new information
     * return List of {@link ServiceRequest}
     * */
    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER')")
    public ServiceRequest updateServiceRequest(@PathVariable long id, @RequestBody ServiceRequestDto serviceRequestDto){
        return serviceRequestService.updateServiceRequest(id, serviceRequestDto);
    }

    /**
     * delete a services request available by its id
     *
     * only for CUSTOMER
     * PathVariable id is the id of the service request we want to delete
     * return 1 if the process is success or 0 if error
     * */
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER')")
    public int deleteServiceRequest(@PathVariable long id){
        return serviceRequestService.deleteServiceRequest(id);
    }

    /**
     * get all services request of a customer logged in
     *
     * return List of {@link ServiceRequest} of the current customer
     * */
    @GetMapping("/my-service-requests")
    public List<ServiceRequest> getMyServiceRequests(){
        return serviceRequestService.getMyServiceRequests();
    }

    /**
     * get all services request of a customer
     *
     * PathVariable customerUsername is the customerUsername of the Customer that the service request we want
     * return {@link ServiceRequest}
     * */
    @GetMapping("/by-customer/{customerUsername}")
    public List<ServiceRequest> getServiceRequestsByCustomer(@PathVariable String customerUsername){
        return serviceRequestService.getServiceRequestsByCustomer(customerUsername);
    }

    /**
     * get all services request of a vehicle
     *
     * PathVariable vehicleId is the vehicleId of the Vehicle that the service request we want
     * return {@link ServiceRequest}
     * */
    @GetMapping("/by-vehicle/{vehicleId}")
    public List<ServiceRequest> getServiceRequestsByVehicle(@PathVariable long vehicleId){
        return serviceRequestService.getServiceRequestsByVehicle(vehicleId);
    }

}
