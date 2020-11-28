package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.dto.LoginRequestDto;
import com.servicethrottle.servicethrottlebackend.security.jwt.JWTProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JWTAuthService {

    private AuthenticationManager authenticationManager;
    private JWTProvider jwtProvider;

    public String login(LoginRequestDto loginRequestDto) throws Exception {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        System.out.println("auth "+ authenticate);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return jwtProvider.generateToken(authenticate);
    }
}
