package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.dto.AuthenticationResponse;
import com.servicethrottle.stuaaservice.dto.FinishRequest;
import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.dto.ResetPasswordRequest;
import com.servicethrottle.stuaaservice.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URISyntaxException;

//REST controller for managing creation of customer accounts as well as
//        verification new account and reset password

@RestController
@RequestMapping("/account")
public class AccountController {

    private final CustomerService customerService;

    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
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
    @GetMapping("/activate")
    public ResponseEntity<AuthenticationResponse> activateAccount(@RequestBody String code)
            throws AccountNotFoundException, URISyntaxException {
        return ResponseEntity.ok().body(customerService.verifyCode(code));
    }


//    resend activation code
//    new activation code will send to the email
    @PutMapping("/resend-code")
    public ResponseEntity<String> resendActivationCode(@RequestBody RegistrationRequest registrationRequest) throws AccountNotFoundException {
        customerService.resendActivationCode(registrationRequest);
        return ResponseEntity.ok().body("Your account activation code have resend");
    }


//    finish new account creation
//    add missing data
//    use FinishRequestDto
    @PutMapping("/finish/")
    public ResponseEntity<String> finishAccount(
            @RequestBody FinishRequest finishRequest)
            throws AccountNotFoundException{
        customerService.finishAccount(finishRequest);
        return ResponseEntity.ok().body("Your account creation success");
    }


//    reset password of an existing Account by email request
//    parameter is the customer email
    @PostMapping("/reset-password/init")
    public ResponseEntity<String> requestPasswordReset(@RequestBody String custEmail){
        customerService.passwordResetEmail(custEmail);
        return ResponseEntity.ok().body("Key have send to your email");
    }


//    password reset finish
//    user ResetPasswordRequest DTO
//    parameters are reset key and newPassword
    @PutMapping("/reset-password/finish")
    public ResponseEntity<String> finishPasswordReset(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        customerService.finishPasswordReset(resetPasswordRequest);
        return ResponseEntity.ok().body("Password reset wsa success");
    }

}
