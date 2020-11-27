package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.dto.RegistrationRequest;
import com.servicethrottle.servicethrottlebackend.services.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/st/account")
@AllArgsConstructor
public class AccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) throws Exception {
        return ResponseEntity.ok().body(userAccountService.registerUser(registrationRequest));
    }

//    activateAccount method if for activate, verify the account of  newly added customer using the verification code
//    verification code send to the email
    @GetMapping("/activate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> activateAccount(@RequestBody String code)
            throws Exception {
        return ResponseEntity.ok().body(userAccountService.activateUser(code));
    }

}
