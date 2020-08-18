package com.servicethrottle.stauthapigateway.controllers;

import com.servicethrottle.stauthapigateway.dto.AuthenticationResponse;
import com.servicethrottle.stauthapigateway.dto.LoginRequest;
import com.servicethrottle.stauthapigateway.exceptions.UsernameOrPasswordInvalidException;
import com.servicethrottle.stauthapigateway.services.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/st")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws Exception, UsernameOrPasswordInvalidException {
        try {
            return authService.login(loginRequest);
        }catch (Exception e){
            throw new UsernameOrPasswordInvalidException(e);
        }
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
