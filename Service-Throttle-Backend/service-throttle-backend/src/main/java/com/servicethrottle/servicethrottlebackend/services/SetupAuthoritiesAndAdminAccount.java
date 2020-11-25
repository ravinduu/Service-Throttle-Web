package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.Admin;
import com.servicethrottle.servicethrottlebackend.models.Authority;
import com.servicethrottle.servicethrottlebackend.models.UserAuthenticationCredentials;
import com.servicethrottle.servicethrottlebackend.repositories.AdminRepository;
import com.servicethrottle.servicethrottlebackend.repositories.UserAuthenticationCredentialsRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.*;

@Component
public class SetupAuthoritiesAndAdminAccount implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final UserAuthenticationCredentialsRepository UACRepo;

    private final AdminRepository adminRepository;
    private final AuthorityService authorityService;

    private final PasswordEncoder passwordEncoder;


    public SetupAuthoritiesAndAdminAccount(AdminRepository adminRepository,
                                           AuthorityService authorityService,
                                           UserAuthenticationCredentialsRepository UACRepo,
                                           PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.authorityService = authorityService;
        this.UACRepo = UACRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(alreadySetup) return;

        Authority adminAuthority =  authorityService.createAuthorityIfNotFound(ADMIN.getAuthorityType());
        Authority supervisorAuthority = authorityService.createAuthorityIfNotFound(SUPERVISOR.getAuthorityType());
        Authority mobileMechanicAuthority = authorityService.createAuthorityIfNotFound(MOBILE_MECHANIC.getAuthorityType());
        Authority customerAuthority = authorityService.createAuthorityIfNotFound(CUSTOMER.getAuthorityType());

        Admin superAdmin = new Admin();
        UserAuthenticationCredentials UAC = new UserAuthenticationCredentials();

        superAdmin.setUsername("admin");
        superAdmin.setEmail("admin@admin.com");
        superAdmin.setSuperAdmin(true);
//        superAdmin.setActivated(true);

        UAC.setUsername("admin");
        UAC.setPassword(encodePassword("admin"));
        Set<Authority> superAdminAuthorities = UAC.getAuthorities();
        superAdminAuthorities.add(adminAuthority);
        superAdminAuthorities.add(supervisorAuthority);
        superAdminAuthorities.add(mobileMechanicAuthority);
        superAdminAuthorities.add(customerAuthority);
        UAC.setAuthorities(superAdminAuthorities);
        UAC.setActivated(true);

        UACRepo.save(UAC);
        adminRepository.save(superAdmin);

        alreadySetup = true;
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

}
