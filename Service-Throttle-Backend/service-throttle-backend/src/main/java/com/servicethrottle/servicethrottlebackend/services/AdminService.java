package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.EmailAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.Admin;
import com.servicethrottle.servicethrottlebackend.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public int registerAdmin(String username, String email) {

        adminRepository.findOneByEmail(email).ifPresent(
                adminExist -> {
                    throw new EmailAlreadyExistException();
                }
        );

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setEmail(email);
//        admin.setActivated(false);
        admin.setCreated(Instant.now());
        adminRepository.save(admin);

        return 1;
    }
}
