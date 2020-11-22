package com.servicethrottle.stuaaservice.services;


import com.servicethrottle.stuaaservice.models.Login;
import com.servicethrottle.stuaaservice.repositories.LoginRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final LoginRepository loginRepository;

    public UserDetailsServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new org.springframework.security.core.userdetails.User(
                login.getUsername(),
                login.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(login));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Login login){
        Set authorities = new HashSet<>();
                login.getAuthorities().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                });
                return authorities;
    }
}
