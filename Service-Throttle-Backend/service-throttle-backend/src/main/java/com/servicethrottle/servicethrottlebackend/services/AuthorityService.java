package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.Authority;
import com.servicethrottle.servicethrottlebackend.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    Authority createAuthorityIfNotFound(String authorityType) {

        Authority authority = authorityRepository.findByAuthority(authorityType);
        if (authority == null) {
            authority = new Authority();
            authority.setAuthority(authorityType);
            authorityRepository.save(authority);
        }
        return authority;
    }
}
