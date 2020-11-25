package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.dto.LoginRequest;
import com.servicethrottle.servicethrottlebackend.models.dto.RegistrationRequest;
import com.servicethrottle.servicethrottlebackend.services.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/st/account")
public class AccountController {

    private final UserAccountService userAccountService;

    public AccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) throws Exception {
        return ResponseEntity.ok().body(userAccountService.registerUser(registrationRequest));
    }

//    activateAccount method if for activate, verify the account of  newly added customer using the verification code
//    verification code send to the email
    @GetMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestBody String code)
            throws Exception {
        return ResponseEntity.ok().body(userAccountService.activateUser(code));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok().body(userAccountService.login(loginRequest));
    }

}
