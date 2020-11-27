package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.UserCredentials;
import com.servicethrottle.servicethrottlebackend.services.AuthorityService;
import com.servicethrottle.servicethrottlebackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/st")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthorityService authorityService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserCredentials>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/authorities")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<String>> getAllAuthorities(){
        return ResponseEntity.ok().body(authorityService.getAllAuthorities());
    }
}
