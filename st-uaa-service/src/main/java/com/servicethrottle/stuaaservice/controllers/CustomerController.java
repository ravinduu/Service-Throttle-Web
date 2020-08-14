package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

//REST controller for managing customer

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    get a customer details  by username
//    access by customer and admin
    @GetMapping("/{username}")
    public Customer getCustomer(@PathVariable ("username") String username) throws AccountNotFoundException {
        return customerService.getCustomer(username);
    }


//    get all customers
//    this method only access by admins
    @GetMapping("/all")
    public List<Customer> getAll(){
        return customerService.getAll();
    }




}
