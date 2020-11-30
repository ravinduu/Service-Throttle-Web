package com.servicethrottle.servicethrottlebackend.controllers;

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

    @GetMapping("/login")
    public ResponseEntity<JWTToken> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        String jwtToken = jwtAuthService.login(loginRequestDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwtToken);
        return ResponseEntity.ok().body(new JWTToken(jwtToken));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class JWTToken {
        private String jwtToken;
    }

    @GetMapping("/hello")
    public String hello(){

        return SecurityUtils.getCurrentUsername().get() + "HELLO";
    }
}