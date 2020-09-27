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
                getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
