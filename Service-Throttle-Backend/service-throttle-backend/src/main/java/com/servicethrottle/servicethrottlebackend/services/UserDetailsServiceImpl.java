package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.UserAuthenticationCredentials;
import com.servicethrottle.servicethrottlebackend.repositories.UserAuthenticationCredentialsRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAuthenticationCredentialsRepository userAuthenticationCredentialsRepository;

    public UserDetailsServiceImpl(UserAuthenticationCredentialsRepository userAuthenticationCredentialsRepository) {
        this.userAuthenticationCredentialsRepository = userAuthenticationCredentialsRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthenticationCredentials userAuthenticationCredentials = userAuthenticationCredentialsRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with "+username));

        return new org.springframework.security.core.userdetails.User(
                userAuthenticationCredentials.getUsername(),
                userAuthenticationCredentials.getPassword(),
                userAuthenticationCredentials.isActivated(),
                true,
                true,
                true,
                getAuthorities(userAuthenticationCredentials));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserAuthenticationCredentials userAuthenticationCredentials){
        Set authorities = new HashSet<>();
                userAuthenticationCredentials.getAuthorities().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getAuthority().toUpperCase()));
                });
                return authorities;
    }
}
