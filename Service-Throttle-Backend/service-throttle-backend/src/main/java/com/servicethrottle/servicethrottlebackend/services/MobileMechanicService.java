package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.EmailAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.MobileMechanic;
import com.servicethrottle.servicethrottlebackend.models.Supervisor;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.repositories.MobileMechanicRepository;
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
@Transactional
@AllArgsConstructor
public class MobileMechanicService {

    private final MobileMechanicRepository mobileMechanicRepository;

    @Transactional(readOnly = true)
    public List<UserDetailsDto> getAllMobileMechanics(){
        return mobileMechanicRepository.findAll().stream().map(UserDetailsDto::new).collect(Collectors.toList());
    }

    public boolean registerMobileMechanic(String username, String email, boolean isLocked) {

        mobileMechanicRepository.findOneByEmail(email).ifPresent(
                customerExist -> {
                    throw new EmailAlreadyExistException();
                }
        );

        MobileMechanic mobileMechanic = new MobileMechanic();
        mobileMechanic.setUsername(username);
        mobileMechanic.setEmail(email);
        mobileMechanic.setCreated(Instant.now());
        mobileMechanic.setLocked(isLocked);
        mobileMechanic.setAssignToTask(false);

        mobileMechanicRepository.save(mobileMechanic);

        return true;
    }

    public void deleteMobileMechanic(String username) {
        mobileMechanicRepository.findOneByUsername(username).ifPresent(
                mobileMechanic -> mobileMechanicRepository.delete(mobileMechanic)
        );
    }

    public MobileMechanic getMobileMechanic(String username) {
        return mobileMechanicRepository.findOneByUsername(username).get();
    }

    public void updateMobileMechanic(UserDetailsDto userDetailsDto) {
        Optional<MobileMechanic> existingMM = mobileMechanicRepository.findOneByEmail(userDetailsDto.getEmail());
        if (existingMM.isPresent() && !existingMM.get().getUsername().toLowerCase().equals(userDetailsDto.getUsername().toLowerCase())) {
            throw new EmailAlreadyExistException();
        }

            mobileMechanicRepository
                .findOneByUsername(userDetailsDto.getUsername())
                .ifPresent(
                        mm -> {
                            mm.setFirstname(userDetailsDto.getFirstname());
                            mm.setLastname(userDetailsDto.getLastname());
                            if (userDetailsDto.getEmail() != null){
                                mm.setEmail(userDetailsDto.getEmail());
                            }
                            mm.setPhoneNumber(userDetailsDto.getPhoneNumber());
                            mm.setAddress(userDetailsDto.getAddress());
                            mobileMechanicRepository.save(mm);
                        }
                );
    }
}
