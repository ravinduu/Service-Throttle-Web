package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.dto.AuthenticationResponseDto;
import com.servicethrottle.servicethrottlebackend.models.dto.LoginRequestDto;
import com.servicethrottle.servicethrottlebackend.security.SecurityUtils;
import com.servicethrottle.servicethrottlebackend.services.JWTAuthService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/st")
@AllArgsConstructor
public class JWTAuthController {

    private JWTAuthService jwtAuthService;

    /**
     * login a user
     *
     * parameter LoginRequestDto - username and password
     * throws 40X if user credentials are wrong, user is locked or user is not activated
     * return {authenticationResponseDto} details of customer
     * */
    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {

        AuthenticationResponseDto authenticationResponseDto = jwtAuthService.login(loginRequestDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + authenticationResponseDto.getJWTToken());
        return ResponseEntity.ok().body(authenticationResponseDto);
    }


    @GetMapping("/hello")
    public String hello(){

        return SecurityUtils.getCurrentUsername().get() + "HELLO";
    }
}
