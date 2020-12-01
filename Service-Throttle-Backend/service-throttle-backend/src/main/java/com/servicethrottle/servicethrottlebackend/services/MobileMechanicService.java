package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.EmailAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.MobileMechanic;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.repositories.MobileMechanicRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class MobileMechanicService {

    private final MobileMechanicRepository mobileMechanicRepository;

    @Transactional(readOnly = true)
    public List<UserDetailsDto> getAllMobileMechanics(){
        return mobileMechanicRepository.findAll().stream().map(UserDetailsDto::new).collect(Collectors.toList());
    }

    public boolean registerMobileMechanic(String username, String email) {

        mobileMechanicRepository.findOneByEmail(email).ifPresent(
                customerExist -> {
                    throw new EmailAlreadyExistException();
                }
        );

        MobileMechanic mobileMechanic = new MobileMechanic();
        mobileMechanic.setUsername(username);
        mobileMechanic.setEmail(email);
        mobileMechanic.setCreated(Instant.now());
        mobileMechanic.setAssignToTask(false);

        mobileMechanicRepository.save(mobileMechanic);

        return true;
    }

    public void deleteMobileMechanic(String username) {
        mobileMechanicRepository.findOneByUsername(username).ifPresent(
                mobileMechanic -> mobileMechanicRepository.delete(mobileMechanic)
        );
    }

    public UserDetailsDto getMobileMechanic(String username) {
        return new UserDetailsDto(mobileMechanicRepository.findOneByUsername(username).get());
    }
}
