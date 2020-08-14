package com.servicethrottle.stuaaservice.services;

import com.servicethrottle.stuaaservice.dto.LoginRequest;
import com.servicethrottle.stuaaservice.models.Login;
import com.servicethrottle.stuaaservice.repositories.LoginRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void createLogin(Login newLogin) {
        loginRepository.save(newLogin);
    }

    public Login authorizeLogin(LoginRequest loginRequest) {
        Login login = loginRepository.findOneByUsername(loginRequest.getUsername()).get();
        return login;
    }
}