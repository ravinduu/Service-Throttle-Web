package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.UsernameAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.ActivationCode;
import com.servicethrottle.servicethrottlebackend.models.Authority;
import com.servicethrottle.servicethrottlebackend.models.UserAuthenticationCredentials;
import com.servicethrottle.servicethrottlebackend.models.dto.LoginRequest;
import com.servicethrottle.servicethrottlebackend.models.dto.RegistrationRequest;
import com.servicethrottle.servicethrottlebackend.repositories.ActivationCodeRepository;
import com.servicethrottle.servicethrottlebackend.repositories.AuthorityRepository;
import com.servicethrottle.servicethrottlebackend.repositories.UserAuthenticationCredentialsRepository;
import com.servicethrottle.servicethrottlebackend.security.jwt.JWTProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.Set;

import static com.servicethrottle.servicethrottlebackend.models.enums.AccountType.ADMIN_ACCOUNT;
import static com.servicethrottle.servicethrottlebackend.models.enums.AccountType.CUSTOMER_ACCOUNT;
import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.ADMIN;
import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.CUSTOMER;

@Service
@Transactional
public class UserAccountService {

    private final UserAuthenticationCredentialsRepository userAuthenticationCredentialsRepository;
    private final AuthorityRepository authorityRepository;
    private final ActivationCodeRepository activationCodeRepository ;

    private final AdminService adminService;
    private final AuthorityService authorityService;
    private final CustomerService customerService;
    private final JWTProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    public UserAccountService(UserAuthenticationCredentialsRepository userAuthenticationCredentialsRepository,
                              AuthorityRepository authorityRepository,
                              ActivationCodeRepository activationCodeRepository, AdminService adminService, AuthorityService authorityService,
                              CustomerService customerService, JWTProvider jwtProvider, AuthenticationManager authenticationManager,
                              PasswordEncoder passwordEncoder) {
        this.userAuthenticationCredentialsRepository = userAuthenticationCredentialsRepository;
        this.authorityRepository = authorityRepository;
        this.activationCodeRepository = activationCodeRepository;
        this.adminService = adminService;
        this.authorityService = authorityService;
        this.customerService = customerService;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegistrationRequest registrationRequest) throws Exception {
        int response = 0;

        String username = registrationRequest.getUsername();
        String email = registrationRequest.getEmail();
        String encodedPassword = encodePassword(registrationRequest.getPassword());
        String accountType = registrationRequest.getAccountType();

        if (username.equals("") || email.equals("") || encodedPassword.equals("")) return "Error";

        userAuthenticationCredentialsRepository.findOneByUsername(username)
                .ifPresent( userExist -> {
                    throw new UsernameAlreadyExistException();
                }
        );

        UserAuthenticationCredentials userAuthenticationCredentials = new UserAuthenticationCredentials();
        userAuthenticationCredentials.setUsername(username);
        userAuthenticationCredentials.setPassword(encodedPassword);
        Set<Authority> userAuthority = userAuthenticationCredentials.getAuthorities();

        if (accountType.equals(ADMIN_ACCOUNT.getAccountType())){
            userAuthority.add(authorityService.createAuthorityIfNotFound(ADMIN.getAuthorityType()));
            response = adminService.registerAdmin(username, email);
        }
        else if (accountType.equals(CUSTOMER_ACCOUNT.getAccountType())){
            userAuthority.add(authorityService.createAuthorityIfNotFound(CUSTOMER.getAuthorityType()));
            response = customerService.registerCustomer(username, email);
        }

        userAuthenticationCredentials.setActivated(false);
        generateActivationCode(userAuthenticationCredentials);
        userAuthenticationCredentialsRepository.save(userAuthenticationCredentials);
//        return login(username, registrationRequest.getPassword());
//        System.out.println(login(new LoginRequest(username,registrationRequest.getPassword())));
        return "bla bla bla";
    }


    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    private ActivationCode generateActivationCode(UserAuthenticationCredentials userAuthenticationCredentials) {
        Random random = new Random();

        String code = String.format("%04d", random.nextInt(10000));

        while (activationCodeRepository.findOneByActivationCode(code).isPresent()){
            code = String.format("%04d", random.nextInt(10000));
        }

//        activation code and customer details save in ActivationCode table
        ActivationCode activationCode = new ActivationCode();
        activationCode.setActivationCode(code);
        activationCode.setUserAuthenticationCredentials(userAuthenticationCredentials);
        activationCodeRepository.save(activationCode);
//        verification code will send to the customer email
//        mailService.sendActivationEmail(activationCode);
        return activationCode;
    }

    public String activateUser(String code) {

        ActivationCode activationCode = activationCodeRepository.findOneByActivationCode(code).get();
        Long id = activationCode.getUserAuthenticationCredentials().getId();

        userAuthenticationCredentialsRepository.findById(id).ifPresent(
                userAuthenticationCredentials -> {
                    userAuthenticationCredentials.setActivated(true);
                    userAuthenticationCredentialsRepository.save(userAuthenticationCredentials);
                }
        );

        activationCodeRepository.delete(activationCode);
        return "Activated";
    }

}
