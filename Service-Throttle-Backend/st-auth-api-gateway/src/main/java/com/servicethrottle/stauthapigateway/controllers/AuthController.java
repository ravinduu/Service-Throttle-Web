package com.servicethrottle.stauthapigateway.controllers;

import com.servicethrottle.stauthapigateway.dto.AuthenticationResponse;
import com.servicethrottle.stauthapigateway.dto.LoginRequest;
import com.servicethrottle.stauthapigateway.exceptions.UsernameOrPasswordInvalidException;
import com.servicethrottle.stauthapigateway.models.Customer;
import com.servicethrottle.stauthapigateway.services.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/st")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

//    login
//    use Login request dto
//    outputs are jwt and username
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws Exception, UsernameOrPasswordInvalidException {
        try {
            return authService.login(loginRequest);
        }catch (Exception e){
            throw new UsernameOrPasswordInvalidException(e);
        }
    }

    @GetMapping("/login/{username}")
    public AuthenticationResponse login(@PathVariable ("username") String username) throws Exception {
        return authService.login(username);
    }

    @GetMapping("/getCurrentUser")
    public Customer getCurrentUser(){
        return authService.getCurrentUser();

    }
}
