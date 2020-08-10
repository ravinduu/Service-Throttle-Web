package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.dto.EditRequest;
import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.io.NotActiveException;

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

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateAccount(@PathVariable ("code") String code)
            throws AccountNotFoundException {
        customerService.verifyCode(code);
        return new ResponseEntity<>(HttpStatus.OK);
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
