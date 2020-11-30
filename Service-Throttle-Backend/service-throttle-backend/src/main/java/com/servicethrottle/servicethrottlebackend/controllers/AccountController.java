package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.models.dto.RegistrationRequestDto;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.services.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/st")
@AllArgsConstructor
public class AccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequestDto registrationRequestDto) throws Exception {
        return ResponseEntity.ok().body(userAccountService.registerUser(registrationRequestDto));
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerAdmin(@RequestBody RegistrationRequestDto registrationRequestDto) throws Exception {
        return ResponseEntity.ok().body(userAccountService.registerAdmin(registrationRequestDto));
    }

//    activateAccount method if for activate, verify the account of  newly added customer using the verification code
//    verification code send to the email
    @GetMapping("/activate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> activateAccount(@RequestBody String code)
            throws Exception {
        return ResponseEntity.ok().body(userAccountService.activateUser(code));
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userAccountService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/lock/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> lockUser(@PathVariable String username){
        return ResponseEntity.ok().body(userAccountService.lockUser(username));
    }

    @PutMapping("/unlock/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> unlockUser(@PathVariable String username){
        return ResponseEntity.ok().body(userAccountService.unlockUser(username));
    }

    //get the current user
    @GetMapping("/account")
    public UserDetailsDto getUser(){
        return userAccountService.getUser();

    }

}
