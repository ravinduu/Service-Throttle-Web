package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.UserAlreadyLockedException;
import com.servicethrottle.servicethrottlebackend.exceptions.UserAlreadyLoggedIn;
import com.servicethrottle.servicethrottlebackend.exceptions.UserNotActivatedException;
import com.servicethrottle.servicethrottlebackend.models.UserCredentials;
import com.servicethrottle.servicethrottlebackend.repositories.UserCredentialsRepository;
import com.servicethrottle.servicethrottlebackend.security.SecurityUtils;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserCredentialsRepository userCredentialsRepository;

    public UserDetailsServiceImpl(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @SneakyThrows
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String lowerCaseUsername = username.toLowerCase(Locale.ENGLISH);
        System.out.println("Here " +SecurityUtils.getCurrentUsername());
//        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) throw new UserAlreadyLoggedIn("Already Logged in");
        return  userCredentialsRepository
                .findOneByUsername(lowerCaseUsername)
                .map(userAuthenticationCredentials -> createSpringSecurityUser(lowerCaseUsername, userAuthenticationCredentials))
                .orElseThrow(() -> new UsernameNotFoundException("User "+lowerCaseUsername+" was not found !!"));
    }

    @SneakyThrows
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowerCaseUsername, UserCredentials userCredentials) {
        if(!userCredentials.isActivated()) {
            throw new UserNotActivatedException();
        }
        if(userCredentials.isLocked()) {
            throw new UserAlreadyLockedException(lowerCaseUsername+" have locked !!");
        }

        return new org.springframework.security.core.userdetails.User(
                userCredentials.getUsername(),
                userCredentials.getPassword(),
                userCredentials.isActivated(),
                true,
                true,
                !userCredentials.isLocked(),
                getAuthorities(userCredentials));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserCredentials userCredentials){
        Set authorities = new HashSet<>();
                userCredentials.getAuthorities().forEach(role -> {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getAuthority().toUpperCase()));
                });
                return authorities;
    }
}
