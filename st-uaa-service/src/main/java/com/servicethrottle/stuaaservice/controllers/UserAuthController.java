package com.servicethrottle.stuaaservice.controllers;

import com.servicethrottle.stuaaservice.dto.LoginRequest;
import com.servicethrottle.stuaaservice.models.Login;
import com.servicethrottle.stuaaservice.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserAuthController {

    private final LoginService loginService;

    public UserAuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity <Login> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(loginService.authorizeLogin(loginRequest),HttpStatus.CREATED);
    }
}
