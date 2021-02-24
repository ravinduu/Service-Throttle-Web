package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.exceptions.NoStVehicleService;
import com.servicethrottle.servicethrottlebackend.models.STVehicleService;
import com.servicethrottle.servicethrottlebackend.models.dto.STVehicleServiceDto;
import com.servicethrottle.servicethrottlebackend.services.STVehicleServiceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/st/vehicle/service")
@AllArgsConstructor
@CrossOrigin("*")
public class STVehicleServiceController {

    private STVehicleServiceService stVehicleServiceService;

    /**
     * add a service
     *
     * only for ADMIN
     * parameter {@link STVehicleServiceDto} is the information about the service
     * return {@link STVehicleService}
     * */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public STVehicleService addVehicleService(@RequestBody  STVehicleServiceDto STVehicleServiceDto){
        return stVehicleServiceService.addVehicleService(STVehicleServiceDto);
    }

    /**
     * get all services available
     *
     * return List of {@link STVehicleService} if available or empty List
     * */
    @GetMapping("/all")
    public List<STVehicleService> getAllStVehicleService(){
        return stVehicleServiceService.getAllStVehicleService();
    }

    /**
     * get a services available by its id
     *
     * PathVariable id is the id of the service we want
     * return a {@link STVehicleService}
     * throw {@link NoStVehicleService} if error occur
     * */
    @GetMapping("/{id}")
    public STVehicleService getAStVehicleService(@PathVariable long id){
        return stVehicleServiceService.getAStVehicleService(id);
    }

    /**
     * update a services available by its id
     *
     * only for ADMIN
     * PathVariable id is the id of the service we want to update
     * RequestBody {@link STVehicleServiceDto} is the new information about the service
     * return a {@link STVehicleService}
     * throw {@link NoStVehicleService} if no {@link STVehicleService} to be found for given id
     * */
    @PutMapping("/{id}/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public STVehicleService updateStVehicleService(@PathVariable long id, @RequestBody STVehicleServiceDto stVehicleServiceDto){
        return stVehicleServiceService.updateStVehicleService(id, stVehicleServiceDto);
    }

    /**
     * delete a services available by its id
     *
     * only for ADMIN
     * PathVariable id is the id of the service we want to delete
     * return 1 if the process is success or 0 if error
     * */
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public int deleteStVehicleService(@PathVariable long id){
        return stVehicleServiceService.deleteStVehicleService(id);
    }
}
