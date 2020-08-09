package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    final
    CustomerService customerService;

    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody RegistrationRequest registrationRequest){
        customerService.registerUser(registrationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
