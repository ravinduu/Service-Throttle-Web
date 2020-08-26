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

//    login
    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
//        use username and pwd authentication
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()
        ));

//        save data in context
        SecurityContextHolder.getContext().setAuthentication(authenticate);

//        get jwt
        String jwtToken = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse(jwtToken,loginRequest.getUsername());
    }

    public AuthenticationResponse login(String username) throws Exception {
//        Authentication authentication = authenticationManager.authenticate(new Username)
        String jwtToken = jwtProvider.generateTokenWithUserName(username);
        return new AuthenticationResponse(jwtToken,username);
    }
}
