package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.MobileMechanic;
import com.servicethrottle.servicethrottlebackend.models.Supervisor;
import com.servicethrottle.servicethrottlebackend.models.UserCredentials;
import com.servicethrottle.servicethrottlebackend.models.dto.AdminDetailsDto;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.services.*;
import lombok.AllArgsConstructor;
import org.dom4j.util.UserDataElement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/st")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthorityService authorityService;
    private final AdminService adminService;
    private final SupervisorService supervisorService;
    private final CustomerService customerService;
    private final MobileMechanicService mobileMechanicService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserCredentials>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/authorities")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<String>> getAllAuthorities(){
        return ResponseEntity.ok().body(authorityService.getAllAuthorities());
    }

    @GetMapping("/users/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDetailsDto>> getAllAdmins(){
        return ResponseEntity.ok().body(adminService.getAllAdmins());
    }

    @GetMapping("/users/supervisor")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERVISOR')")
    public ResponseEntity<List<UserDetailsDto>> getAllSupervisors(){
        return ResponseEntity.ok().body(supervisorService.getAllSupervisors());
    }

    @GetMapping("/users/mobile-mechanic")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERVISOR')")
    public ResponseEntity<List<UserDetailsDto>> getAllMobileMechanics(){
        return ResponseEntity.ok().body(mobileMechanicService.getAllMobileMechanics());
    }

    @GetMapping("/users/customer")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERVISOR')")
    public ResponseEntity<List<UserDetailsDto>> getAllCustomers(){
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    /**
     * get details of a admin
     *
     * parameter username use to fetch info from db
     * ROLE_ADMIN requires
     * return {userDetailsDto} details of admin
     * */
    @GetMapping("/user/admin/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDetailsDto> getAdmin(@PathVariable String username){
        return ResponseEntity.ok().body(adminService.getAdmin(username));
    }

    /**
     * get details of a supervisor
     *
     * parameter username use to fetch info from db
     * ROLE_ADMIN requires
     * return {userDetailsDto} details of supervisor
     * */
    @GetMapping("/user/supervisor/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDetailsDto> getSupervisor(@PathVariable String username){
        return ResponseEntity.ok().body(supervisorService.getSupervisor(username));
    }

    /**
     * get details of a mobile-mechanic
     *
     * parameter username use to fetch info from db
     * ROLE_ADMIN, ROLE_SUPERVISOR, ROLE_MM or ROLE_CUSTOMER requires
     * return {userDetailsDto} details of MM
     * */
    @GetMapping("/user/mobile-mechanic/{username}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERVISOR','ROLE_MOBILEMECHANIC','ROLE_CUSTOMER')")
    public ResponseEntity<UserDetailsDto> getMobileMechanic(@PathVariable String username){
        return ResponseEntity.ok().body(mobileMechanicService.getMobileMechanic(username));
    }

    /**
     * get details of a customer
     *
     * parameter username use to fetch info from db
     * ROLE_ADMIN, ROLE_SUPERVISOR or ROLE_MM requires
     * return {userDetailsDto} details of customer
     * */
    @GetMapping("/user/customer/{username}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SUPERVISOR','ROLE_MOBILEMECHANIC')")
    public ResponseEntity<UserDetailsDto> getCustomer(@PathVariable String username){
        return ResponseEntity.ok().body(customerService.getCustomer(username));
    }

}
