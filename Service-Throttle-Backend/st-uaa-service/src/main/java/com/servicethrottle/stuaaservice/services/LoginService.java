package com.servicethrottle.stuaaservice.services;

import com.servicethrottle.stuaaservice.dto.AuthenticationResponse;
import com.servicethrottle.stuaaservice.dto.LoginRequest;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.models.Login;
import com.servicethrottle.stuaaservice.repositories.LoginRepository;
import com.servicethrottle.stuaaservice.security.jwt.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    private final LoginRepository loginRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public LoginService(LoginRepository loginRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.loginRepository = loginRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public void createLogin(Login newLogin) {
        loginRepository.save(newLogin);
    }

    public void deleteLogin(Customer customer) {
        Login login = loginRepository.findOneByUsername(customer.getCustUsername()).get();
        loginRepository.delete(login);
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
}
