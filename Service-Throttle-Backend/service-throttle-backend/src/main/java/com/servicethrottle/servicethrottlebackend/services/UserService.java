package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.UserCredentials;
import com.servicethrottle.servicethrottlebackend.repositories.UserCredentialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserCredentialsRepository userCredentialsRepository;

    public List<UserCredentials> getAllUsers() {
        return userCredentialsRepository.findAll();
    }
}
