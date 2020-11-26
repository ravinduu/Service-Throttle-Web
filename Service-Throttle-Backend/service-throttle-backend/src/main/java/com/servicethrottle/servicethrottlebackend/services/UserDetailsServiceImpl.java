package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.UserNotActivatedException;
import com.servicethrottle.servicethrottlebackend.models.UserAuthenticationCredentials;
import com.servicethrottle.servicethrottlebackend.repositories.UserAuthenticationCredentialsRepository;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAuthenticationCredentialsRepository userAuthenticationCredentialsRepository;

    public UserDetailsServiceImpl(UserAuthenticationCredentialsRepository userAuthenticationCredentialsRepository) {
        this.userAuthenticationCredentialsRepository = userAuthenticationCredentialsRepository;
    }

    @SneakyThrows
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String lowerCaseUsername = username.toLowerCase(Locale.ENGLISH);

        return  userAuthenticationCredentialsRepository
                .findOneByUsername(lowerCaseUsername)
                .map(userAuthenticationCredentials -> createSpringSecurityUser(lowerCaseUsername, userAuthenticationCredentials))
                .orElseThrow(() -> new UsernameNotFoundException("User "+lowerCaseUsername+" was not found !!"));
    }

    @SneakyThrows
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowerCaseUsername, UserAuthenticationCredentials userAuthenticationCredentials) {
        if(!userAuthenticationCredentials.isActivated()) {
            throw new UserNotActivatedException();
        }

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
