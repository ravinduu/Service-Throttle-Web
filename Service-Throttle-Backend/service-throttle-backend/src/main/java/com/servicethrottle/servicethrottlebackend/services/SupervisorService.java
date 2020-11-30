package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.EmailAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.Supervisor;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.repositories.SupervisorRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
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
            supervisorRepository.findOneByUsername(username).ifPresent(
                    UserDetailsDto::new
            );
            throw new UsernameNotFoundException("User could not be found");
    }
}
