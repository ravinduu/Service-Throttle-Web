package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.dto.EditRequest;
import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.services.CustomerService;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;
import java.io.NotActiveException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final RestTemplate restTemplate;

    private final CustomerService customerService;

    public AccountController(CustomerService customerService, RestTemplate restTemplate) {
        this.customerService = customerService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody RegistrationRequest registrationRequest){
        customerService.registerUser(registrationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/activate/{code}")
    public Customer activateAccount(@PathVariable ("code") String code)
            throws AccountNotFoundException, URISyntaxException {
        return restTemplate.getForObject("http://localhost:8081/customer/"+customerService.verifyCode(code), Customer.class);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editAccount(@RequestBody EditRequest editRequest)
            throws AccountNotFoundException{
        customerService.editAccount(editRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(/*password change request*/){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reset-password/init")
    public ResponseEntity<String> requestPasswordReset(/*password change request*/){
        //this send email for change password
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reset-password/finish")
    public ResponseEntity<String> finishPasswordReset(/*RequestBody KeyAndPasswordVM keyAndPassword*/) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
