package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.Account;
import com.servicethrottle.servicethrottlebackend.models.Authority;
import com.servicethrottle.servicethrottlebackend.models.UserAuthenticationCredentials;
import com.servicethrottle.servicethrottlebackend.models.dto.RegistrationRequest;
import com.servicethrottle.servicethrottlebackend.repositories.AuthorityRepository;
import com.servicethrottle.servicethrottlebackend.repositories.UserAuthenticationCredentialsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.servicethrottle.servicethrottlebackend.models.enums.AccountType.ADMIN_ACCOUNT;
import static com.servicethrottle.servicethrottlebackend.models.enums.AccountType.CUSTOMER_ACCOUNT;
import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.ADMIN;

@Service
@Transactional
public class UserAccountService {

    private final UserAuthenticationCredentialsRepository userAuthenticationCredentialsRepository;
    private final AuthorityRepository authorityRepository;

    private final AdminService adminService;
    private final AuthorityService authorityService;


    public UserAccountService(UserAuthenticationCredentialsRepository userAuthenticationCredentialsRepository,
                              AuthorityRepository authorityRepository,
                              AdminService adminService, AuthorityService authorityService) {
        this.userAuthenticationCredentialsRepository = userAuthenticationCredentialsRepository;
        this.authorityRepository = authorityRepository;
        this.adminService = adminService;
        this.authorityService = authorityService;

    }

    public int registerUser(RegistrationRequest registrationRequest){
        int response = 0;

        String username = registrationRequest.getUsername();
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        String accountType = registrationRequest.getAccountType();

        if (username.equals(null) || email.equals(null) || password.equals(null)) return 0;


        UserAuthenticationCredentials userAuthenticationCredentials = new UserAuthenticationCredentials();
        userAuthenticationCredentials.setUsername(username);
        userAuthenticationCredentials.setPassword(password);
        Set<Authority> userAuthority = userAuthenticationCredentials.getAuthorities();


        if (accountType.equals(ADMIN_ACCOUNT.getAccountType())){
            userAuthority.add(authorityService.createAuthorityIfNotFound(ADMIN.getAuthorityType()));
            response = adminService.registerAdmin(username, email);
        }
        else if (accountType.equals(CUSTOMER_ACCOUNT.getAccountType())){
            System.out.println("Customer");
        }

        userAuthenticationCredentialsRepository.save(userAuthenticationCredentials);
        return response;
    }
}
