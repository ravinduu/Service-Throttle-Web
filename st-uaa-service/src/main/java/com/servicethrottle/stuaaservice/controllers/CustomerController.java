package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{username}")
    public Customer getCustomer(@PathVariable ("username") String username) throws AccountNotFoundException {
        return customerService.getCustomer(username);
    }
}
