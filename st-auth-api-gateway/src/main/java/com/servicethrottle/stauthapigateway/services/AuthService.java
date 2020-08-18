package com.servicethrottle.stauthapigateway.services;

import com.servicethrottle.stauthapigateway.dto.AuthenticationResponse;
import com.servicethrottle.stauthapigateway.dto.LoginRequest;
import com.servicethrottle.stauthapigateway.security.jwt.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    public AuthService(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String jwtToken = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse(jwtToken,loginRequest.getUsername());
    }
}
