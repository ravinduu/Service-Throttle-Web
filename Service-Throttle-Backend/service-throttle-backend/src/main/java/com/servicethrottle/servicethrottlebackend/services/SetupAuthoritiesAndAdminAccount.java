package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.Admin;
import com.servicethrottle.servicethrottlebackend.models.Authority;
import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.UserAuthenticationCredentials;
import com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType;
import com.servicethrottle.servicethrottlebackend.repositories.AdminRepository;
import com.servicethrottle.servicethrottlebackend.repositories.AuthorityRepository;
import com.servicethrottle.servicethrottlebackend.repositories.CustomerRepository;
import com.servicethrottle.servicethrottlebackend.repositories.UserAuthenticationCredentialsRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.*;

@Component
public class SetupAuthoritiesAndAdminAccount implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final AdminRepository adminRepository;
    private final AuthorityRepository authorityRepository;
    private final UserAuthenticationCredentialsRepository UACRepo;
    private final CustomerRepository customerRepository;

    public SetupAuthoritiesAndAdminAccount(AdminRepository adminRepository, AuthorityRepository authorityRepository, UserAuthenticationCredentialsRepository UACRepo, CustomerRepository customerRepository) {
        this.adminRepository = adminRepository;
        this.authorityRepository = authorityRepository;
        this.UACRepo = UACRepo;
        this.customerRepository = customerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(alreadySetup) return;

        Authority adminAuthority =  createAuthorityIfNotFound(ADMIN);
        Authority supervisorAuthority = createAuthorityIfNotFound(SUPERVISOR);
        Authority mobileMechanicAuthority = createAuthorityIfNotFound(MOBILE_MECHANIC);
        Authority customerAuthority = createAuthorityIfNotFound(CUSTOMER);

        Admin superAdmin = new Admin();
        UserAuthenticationCredentials UAC = new UserAuthenticationCredentials();

        superAdmin.setUsername("admin");
        superAdmin.setEmail("admin@admin.com");
        Set<Authority> superAdminAuthorities = UAC.getAuthorities();
        superAdminAuthorities.add(adminAuthority);
        superAdminAuthorities.add(adminAuthority);
        superAdmin.setSuperAdmin(true);
        superAdmin.setActivated(true);

        UAC.setUsername("admin");
        UAC.setPassword("admin");
        UAC.setAuthorities(superAdminAuthorities);

        UACRepo.save(UAC);
        adminRepository.save(superAdmin);

        Customer newCustomer = new Customer();
        UserAuthenticationCredentials UACOne = new UserAuthenticationCredentials();
        newCustomer.setUsername("customer");
        newCustomer.setEmail("customer@customer.com");

        Set<Authority> customerAuthorities = UACOne.getAuthorities();
        customerAuthorities.add(customerAuthority);
        newCustomer.setActivated(true);

        UACOne.setUsername("customer");
        UACOne.setPassword("customer");
        UACOne.setAuthorities(customerAuthorities);
        customerRepository.save(newCustomer);
        UACRepo.save(UACOne);

        alreadySetup = true;

    }

    @Transactional
    Authority createAuthorityIfNotFound(AuthorityType authorityType) {

        Authority authority = authorityRepository.findByAuthority(authorityType);
        if (authority == null) {
            authority = new Authority();
            authority.setAuthority(authorityType);
            authorityRepository.save(authority);
        }
        return authority;
    }
}
