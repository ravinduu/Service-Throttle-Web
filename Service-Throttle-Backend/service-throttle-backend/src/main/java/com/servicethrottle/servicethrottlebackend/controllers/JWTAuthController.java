package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.dto.AuthenticationResponseDto;
import com.servicethrottle.servicethrottlebackend.models.dto.LoginRequestDto;
import com.servicethrottle.servicethrottlebackend.services.CustomerService;
import com.servicethrottle.servicethrottlebackend.services.JWTAuthService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

@RestController
@RequestMapping("/st")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class JWTAuthController {

    /**
     * TO-DO
     * implement logout function
     * */

    private final Logger log = LoggerFactory.getLogger(JWTAuthController.class);
    private JWTAuthService jwtAuthService;

    /**
     * login a user
     *
     * parameter LoginRequestDto - username and password
     * throws 40X if user credentials are wrong, user is locked or user is not activated
     * return {authenticationResponseDto} details of customer
     * */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        log.info(loginRequestDto.getUsername()+" sends login request to the backend");
        AuthenticationResponseDto authenticationResponseDto = jwtAuthService.login(loginRequestDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + authenticationResponseDto.getJWTToken());
        return ResponseEntity.ok().body(authenticationResponseDto);
    }

    @PostMapping("/logout")
    public String login() {
        SecurityContextHolder.clearContext();
        return "Logout";
    }

    @GetMapping("/hello")
    public String hello(){
        log.info("/hello endpoint called");
        return "HELLO USER!!!!";
    }
}
