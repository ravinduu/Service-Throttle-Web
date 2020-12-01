package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.EmailAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.Admin;
import com.servicethrottle.servicethrottlebackend.models.Supervisor;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.repositories.SupervisorRepository;
import com.servicethrottle.servicethrottlebackend.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class SupervisorService {

    private final SupervisorRepository supervisorRepository;

    @Transactional(readOnly = true)
    public List<UserDetailsDto> getAllSupervisors() {
        return supervisorRepository.findAll().stream().map(UserDetailsDto::new).collect(Collectors.toList());
    }

    public boolean registerSupervisor(String username, String email) {
        supervisorRepository.findOneByEmail(email).ifPresent(
                customerExist -> {
                    throw new EmailAlreadyExistException();
                }
        );

        Supervisor newSupervisor = new Supervisor();
        newSupervisor.setUsername(username);
        newSupervisor.setEmail(email);
        newSupervisor.setCreated(Instant.now());
        newSupervisor.setAvailable(true);

        supervisorRepository.save(newSupervisor);

        return true;
    }

    public void deleteSupervisor(String username) {
        supervisorRepository.findOneByUsername(username).ifPresent(
                supervisor -> supervisorRepository.delete(supervisor)
        );
    }

    public UserDetailsDto getSupervisor(String username) {
        return new UserDetailsDto(supervisorRepository.findOneByUsername(username).get());
    }

    public void updateSupervisor(UserDetailsDto userDetailsDto) {
        Optional<Supervisor> existingSupervisor = supervisorRepository.findOneByEmail(userDetailsDto.getEmail());
        if (existingSupervisor.isPresent() && !existingSupervisor.get().getUsername().toLowerCase().equals(userDetailsDto.getUsername().toLowerCase())) {
            throw new EmailAlreadyExistException();
        }

        SecurityUtils.getCurrentUsername()
                .flatMap(supervisorRepository::findOneByUsername)
                .ifPresent(
                        supervisor -> {
                            supervisor.setFirstname(userDetailsDto.getFirstname());
                            supervisor.setLastname(userDetailsDto.getLastname());
                            if (userDetailsDto.getEmail() != null){
                                supervisor.setEmail(userDetailsDto.getEmail());
                            }
                            supervisor.setPhoneNumber(userDetailsDto.getPhoneNumber());
                            supervisor.setAddress(userDetailsDto.getAddress());
                            supervisorRepository.save(supervisor);
                        }
                );
    }
}