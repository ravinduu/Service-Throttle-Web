package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.models.Admin;
import com.servicethrottle.servicethrottlebackend.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public int registerAdmin(String username, String email) {
        Admin admin = new Admin();

        admin.setUsername(username);
        admin.setEmail(email);

        adminRepository.save(admin);
        return 0;
    }
}
