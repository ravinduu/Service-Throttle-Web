package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.AccountResourceException;
import com.servicethrottle.servicethrottlebackend.exceptions.UsernameAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.ActivationCode;
import com.servicethrottle.servicethrottlebackend.models.Authority;
import com.servicethrottle.servicethrottlebackend.models.UserCredentials;
import com.servicethrottle.servicethrottlebackend.models.dto.RegistrationRequestDto;
import com.servicethrottle.servicethrottlebackend.repositories.ActivationCodeRepository;
import com.servicethrottle.servicethrottlebackend.repositories.AuthorityRepository;
import com.servicethrottle.servicethrottlebackend.repositories.UserCredentialsRepository;
import com.servicethrottle.servicethrottlebackend.security.jwt.JWTProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static com.servicethrottle.servicethrottlebackend.models.enums.AccountType.ADMIN_ACCOUNT;
import static com.servicethrottle.servicethrottlebackend.models.enums.AccountType.CUSTOMER_ACCOUNT;
import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.ADMIN;
import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.CUSTOMER;

@Service
@Transactional
public class UserAccountService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final AuthorityRepository authorityRepository;
    private final ActivationCodeRepository activationCodeRepository ;

    private final AdminService adminService;
    private final AuthorityService authorityService;
    private final CustomerService customerService;
    private final JWTProvider jwtProvider;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;


    public UserAccountService(UserCredentialsRepository userCredentialsRepository,
                              AuthorityRepository authorityRepository,
                              ActivationCodeRepository activationCodeRepository, AdminService adminService, AuthorityService authorityService,
                              CustomerService customerService, JWTProvider jwtProvider, AuthenticationManager authenticationManager,
                              PasswordEncoder passwordEncoder) {
        this.userCredentialsRepository = userCredentialsRepository;
        this.authorityRepository = authorityRepository;
        this.activationCodeRepository = activationCodeRepository;
        this.adminService = adminService;
        this.authorityService = authorityService;
        this.customerService = customerService;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(RegistrationRequestDto registrationRequestDto) throws Exception {
        int response = 0;

        String username = registrationRequestDto.getUsername();
        String email = registrationRequestDto.getEmail();
        String encodedPassword = encodePassword(registrationRequestDto.getPassword());
        String accountType = registrationRequestDto.getAccountType();

        if (username.equals("") || email.equals("") || encodedPassword.equals("")) return "Error";

        userCredentialsRepository.findOneByUsername(username)
                .ifPresent( userExist -> {
                    throw new UsernameAlreadyExistException();
                }
        );

        UserCredentials userCredentials = new UserCredentials();
        userCredentials.setUsername(username);
        userCredentials.setPassword(encodedPassword);

        Set<Authority> userAuthority = userCredentials.getAuthorities();

        if (accountType.equals(ADMIN_ACCOUNT.getAccountType())){
            userCredentials.setAccountType(ADMIN_ACCOUNT.getAccountType());
            userAuthority.add(authorityService.createAuthorityIfNotFound(ADMIN.getAuthorityType()));
            response = adminService.registerAdmin(username, email);
        }
        else if (accountType.equals(CUSTOMER_ACCOUNT.getAccountType())){
            userCredentials.setAccountType(CUSTOMER_ACCOUNT.getAccountType());
            userAuthority.add(authorityService.createAuthorityIfNotFound(CUSTOMER.getAuthorityType()));
            response = customerService.registerCustomer(username, email);
        }

//        userCredentials.setActivated(false);
        generateActivationCode(userCredentials);
        userCredentialsRepository.save(userCredentials);
//        return login(username, registrationRequestDto.getPassword());
//        System.out.println(login(new LoginRequestDto(username,registrationRequestDto.getPassword())));
        return "bla bla bla";
    }


    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    private ActivationCode generateActivationCode(UserCredentials userCredentials) {
        Random random = new Random();

        String code = String.format("%04d", random.nextInt(10000));

        while (activationCodeRepository.findOneByActivationCode(code).isPresent()){
            code = String.format("%04d", random.nextInt(10000));
        }

//        activation code and customer details save in ActivationCode table
        ActivationCode activationCode = new ActivationCode();
        activationCode.setActivationCode(code);
        activationCode.setUserCredentials(userCredentials);
        activationCodeRepository.save(activationCode);
//        verification code will send to the customer email
//        mailService.sendActivationEmail(activationCode);
        return activationCode;
    }

    public String activateUser(String code) {

        Optional<ActivationCode> activationCode = activationCodeRepository.findOneByActivationCode(code);

        if (!activationCode.isPresent()) {
            throw new AccountResourceException("No user was found for this activation code");
        }

        activationCode.map(
                activationCode1 -> {
                    userCredentialsRepository.findById(activationCode.get().getUserCredentials().getId()).map(
                            userCredentials -> {
                                userCredentials.setActivated(true);
                                userCredentials.setLocked(false);
                                userCredentialsRepository.save(userCredentials);
                                activationCodeRepository.delete(activationCode1);
                                return "Activated";
                            }
                    );
                    return "No user was found for this activation code";
                }
        );
//
//
//
//        userCredentialsRepository.findById(id).ifPresent(
//                userAuthenticationCredentials -> {
//                    userAuthenticationCredentials.setActivated(true);
//                    userAuthenticationCredentials.setLocked(false);
//                    userCredentialsRepository.save(userAuthenticationCredentials);
//                }
//        );
//
//        activationCodeRepository.delete(activationCode);
        return "Activated";
    }

}
