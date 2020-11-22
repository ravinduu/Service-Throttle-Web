package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.Admin;
import com.servicethrottle.servicethrottlebackend.models.Authority;
import com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType;
import com.servicethrottle.servicethrottlebackend.repositories.AdminRepository;
import com.servicethrottle.servicethrottlebackend.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public SetupAuthoritiesAndAdminAccount(AdminRepository adminRepository, AuthorityRepository authorityRepository) {
        this.adminRepository = adminRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(alreadySetup) return;

        Authority adminAuthority =  createAuthorityIfNotFound(ADMIN);
        Authority supervisorAuthority = createAuthorityIfNotFound(SUPERVISOR);
        Authority mobileMechanicAuthority = createAuthorityIfNotFound(MOBILE_MECHANIC);
        Authority customerAuthority = createAuthorityIfNotFound(CUSTOMER);

        Admin superAdmin = new Admin();

        superAdmin.setUsername("admin");
        superAdmin.setPassword("admin");
        superAdmin.setEmail("admin@admin.com");
        Set<Authority> superAdminAuthorities = superAdmin.getAuthorities();
        superAdminAuthorities.add(adminAuthority);
        superAdmin.setAuthorities(superAdminAuthorities);
        superAdmin.setSuperAdmin(true);
        superAdmin.setActivated(true);

        adminRepository.save(superAdmin);
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
