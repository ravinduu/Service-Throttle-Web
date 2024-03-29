package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.EmailAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.exceptions.SuperAdminException;
import com.servicethrottle.servicethrottlebackend.models.Admin;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.repositories.AdminRepository;
import com.servicethrottle.servicethrottlebackend.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean registerAdmin(String username, String email) {

        adminRepository.findOneByEmail(email).ifPresent(
                adminExist -> {
                    throw new EmailAlreadyExistException();
                }
        );

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setCreated(Instant.now());
        adminRepository.save(admin);

        return true;
    }

    @Transactional(readOnly = true)
    public List<UserDetailsDto> getAllAdmins() {
        return adminRepository.findAll().stream().map(UserDetailsDto::new).collect(Collectors.toList());
    }

    public void deleteAdmin(String username) {

        adminRepository.findOneByUsername(username).ifPresent(
                admin -> {
                    if (admin.isSuperAdmin() == true) throw new SuperAdminException("Super Admin Accounts Cannot Delete !!");
                    adminRepository.delete(admin);
                }
        );
    }

    @Transactional(readOnly = true)
    public UserDetailsDto getAdmin(String username) {
        return new UserDetailsDto(adminRepository.findOneByUsername(username).get());
    }

    public void updateAdmin(UserDetailsDto userDetailsDto) {
        Optional<Admin> existingAdmin = adminRepository.findOneByEmail(userDetailsDto.getEmail());
        if (existingAdmin.isPresent() && !existingAdmin.get().getUsername().toLowerCase().equals(userDetailsDto.getUsername().toLowerCase())){
            throw new EmailAlreadyExistException();
        }
            adminRepository
                .findOneByUsername(userDetailsDto.getUsername())
                .ifPresent(admin -> {
                    admin.setFirstname(userDetailsDto.getFirstname());
                    admin.setLastname(userDetailsDto.getLastname());
                    if (userDetailsDto.getEmail() != null){
                        admin.setEmail(userDetailsDto.getEmail());
                    }
                    admin.setPhoneNumber(userDetailsDto.getPhoneNumber());
                    admin.setAddress(userDetailsDto.getAddress());
                    adminRepository.save(admin);
                });
    }
}
