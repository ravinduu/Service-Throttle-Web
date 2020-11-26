package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.dto.LoginRequest;
import com.servicethrottle.servicethrottlebackend.security.jwt.JWTProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.remote.JMXAuthenticator;

@Service
@AllArgsConstructor
public class JWTAuthService {

    private AuthenticationManager authenticationManager;
    private JWTProvider jwtProvider;

    public String login(LoginRequest loginRequest) throws Exception {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("auth "+ authenticate);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return jwtProvider.generateToken(authenticate);
    }
}
