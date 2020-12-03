package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.dto.AuthenticationResponseDto;
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

    public AuthenticationResponseDto login(LoginRequestDto loginRequestDto) throws Exception {
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String JWTToken = jwtProvider.generateToken(authenticate);

        authenticationResponseDto.setJWTToken(JWTToken);
        authenticationResponseDto.setUsername(loginRequestDto.getUsername());


        return authenticationResponseDto;
    }
}
