package com.servicethrottle.servicethrottlebackend.controllers;

import com.servicethrottle.servicethrottlebackend.exceptions.AccountResourceException;
import com.servicethrottle.servicethrottlebackend.models.dto.PasswordResetDto;
import com.servicethrottle.servicethrottlebackend.models.dto.RegistrationRequestDto;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.services.UserAccountService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
REST controller for managing user accounts.
*/

@RestController
@RequestMapping("/st")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    private final UserAccountService userAccountService;

    /**
     * registration of a new user
     *
     * only for CUSTOMER, SUPERVISOR and MOBILE MECHANIC
     * parameter RegistrationRequestDto is the credential information of the new user
     * a verification code will send to the email
     * must have the account_type
     * */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequestDto registrationRequestDto) throws Exception {
        return ResponseEntity.ok().body(userAccountService.registerUser(registrationRequestDto));
    }

    /**
     * registration of a new admin
     *
     * ROLE_ADMIN requires
     * parameter RegistrationRequestDto is the credential information of the new admin
     * must have the account_type
     * */
    @PostMapping("/register/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerAdmin(@RequestBody RegistrationRequestDto registrationRequestDto) throws Exception {
        return ResponseEntity.ok().body(userAccountService.registerAdmin(registrationRequestDto));
    }

    /**
     * activation pf the registered user
     *
     * verify the account of  newly added customer using the verification code
     * parameter is the activation code
     * throws AccountResourceException if the code is an invalid one
     *
     * */
    @GetMapping("/activate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> activateAccount(@RequestBody String code)
            throws Exception {
        return ResponseEntity.ok().body(userAccountService.activateUser(code));
    }

    /**
     * delete an existing user
     *
     * ROLE_ADMIN requires
     * parameter is username of the user
     * throws SuperAdminException if the user to delete is a SUPER ADMIN
     * */
    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        userAccountService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    /**
     * locks an existing user
     *
     * ROLE_ADMIN requires
     * parameter is username of the user
     * throws UserAlreadyLockedException if user already locked
     *
     * */
    @PutMapping("/lock/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> lockUser(@PathVariable String username){
        return ResponseEntity.ok().body(userAccountService.lockUser(username));
    }

    /**
     * unlocks an existing user
     *
     * ROLE_ADMIN requires
     * parameter is username of the user
     * throws UserAlreadyLockedException if user already unlocked
     *
     * */
    @PutMapping("/unlock/{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> unlockUser(@PathVariable String username){
        return ResponseEntity.ok().body(userAccountService.unlockUser(username));
    }

    /**
     * get the current logged user
     *
     * uses SecurityUtils
     * return {userDetailsDto} the current user
     * throws UsernameNotFoundException if the no user logged in
    * */
    @GetMapping("/account/{username}")
    public UserDetailsDto getUser(@PathVariable String username){
        System.out.println("Here username" + username);
        return userAccountService.getUser(username);

    }

    /**
     * update the current user basic information.
     *
     * parameter userDTO is the current user information.
     * throws EmailAlreadyUsedException  if the email is already used.
     * throws RuntimeException  if the user login wasn't found.
     */
    @PostMapping("/account")
    public ResponseEntity<String> saveAccount(@RequestBody UserDetailsDto userDetailsDto) throws AccountResourceException {
        return ResponseEntity.ok().body(userAccountService.updateUser(userDetailsDto));
    }

    @PostMapping("/account/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateAccount(@RequestBody UserDetailsDto userDetailsDto) throws AccountResourceException {
        return ResponseEntity.ok().body(userAccountService.updateExistingUser(userDetailsDto));
    }

    /**
     * reset the password of the current user.
     *
     * parameter PasswordResetDto old and new password
     * throws InvalidPasswordException if the old password dont matches.
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetDto passwordResetDto){
        return ResponseEntity.ok().body(userAccountService.resetPassword(passwordResetDto));
    }
}
