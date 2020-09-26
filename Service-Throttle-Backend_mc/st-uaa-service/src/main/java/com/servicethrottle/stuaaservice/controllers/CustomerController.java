package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.dto.EditRequest;
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

//    delete customer
//    can access by account owner as well as the admins
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteCustomer(@PathVariable ("username") String username) throws AccountNotFoundException {
        customerService.deleteCustomer(username);
        return ResponseEntity.ok().body(username+" was successfully deleted");
    }

//    edit details of the current customer
//    param is edit request dto
    @PutMapping("/edit")
    public ResponseEntity<String> editCustomer(@RequestBody EditRequest editRequest) throws AccountNotFoundException {
        customerService.editCustomer(editRequest);
        return ResponseEntity.ok().body(editRequest.getCustUsername()+" was successfully updated");
    }

//    deactivate existing customer
//    access by ADMIN
    @PutMapping("/deactivate/{username}")
    public ResponseEntity<String> deactivateCustomer(@PathVariable ("username") String username) throws AccountNotFoundException {
        customerService.deactivateCustomer(username);
        return ResponseEntity.ok().body(username+" was temporarily deactivated");
    }
}
