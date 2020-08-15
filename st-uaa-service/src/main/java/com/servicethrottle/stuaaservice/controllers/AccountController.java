package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.dto.FinishRequest;
import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.dto.ResetPasswordRequest;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URISyntaxException;

//REST controller for managing creation of customer accounts as well as
//        verification new account and reset password

@RestController
@RequestMapping("/account")
public class AccountController {

    private final RestTemplate restTemplate;

    private final CustomerService customerService;

    public AccountController(CustomerService customerService, RestTemplate restTemplate) {
        this.customerService = customerService;
        this.restTemplate = restTemplate;
    }


//    registerAccount method is for add new customer to the system
//    verification code is send to the email
//    can access by anyone
//    inputs are username, password and email, RegistrationRequest DTO
    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody RegistrationRequest registrationRequest){
        customerService.registerUser(registrationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    activateAccount method if for activate, verify the account of  newly added customer using the verification code
//    verification code send to the email
    @GetMapping("/activate/{code}")
    public Customer activateAccount(@PathVariable ("code") String code)
            throws AccountNotFoundException, URISyntaxException {
        return restTemplate.getForObject("http://localhost:8081/customer/"+customerService.verifyCode(code), Customer.class);
    }

//    resend activation code
//    new activation code will send to the email
    @PutMapping("/resend-code")
    public ResponseEntity<String> resendActivationCode(@RequestBody RegistrationRequest registrationRequest) throws AccountNotFoundException {
        customerService.resendActivationCode(registrationRequest);
        return ResponseEntity.ok().body("Resend Code");
    }

//    finish new account creation
//    add missing data
//    use FinishRequestDto
    @PutMapping("/finish/{username}")
    public ResponseEntity<String> finishAccount(
            @RequestBody FinishRequest finishRequest,
            @PathVariable ("username") String username)
            throws AccountNotFoundException{
        customerService.finishAccount(finishRequest, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    reset password of an existing Account by email request
//    parameter is the customer email
    @PostMapping("/reset-password/init")
    public ResponseEntity<String> requestPasswordReset(@RequestBody String custEmail){
        customerService.passwordResetEmail(custEmail);
        return ResponseEntity.ok().body("Key send to email");
    }


//    password reset finish
//    user ResetPasswordRequest DTO
//    parameters are resetkey and newPassword
    @PutMapping("/reset-password/finish")
    public ResponseEntity<String> finishPasswordReset(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        customerService.finishPasswordReset(resetPasswordRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
