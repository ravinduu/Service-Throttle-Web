package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.EmailAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.repositories.CustomerRepository;
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
public class CustomerService {
    private final CustomerRepository customerRepository;


    public boolean registerCustomer(String username, String email) {
        customerRepository.findOneByEmail(email).ifPresent(
                customerExist -> {
                    throw new EmailAlreadyExistException();
                }
        );

        Customer newCustomer = new Customer();
        newCustomer.setUsername(username);
        newCustomer.setEmail(email);
        newCustomer.setCreated(Instant.now());
        newCustomer.setLoyaltyPoints(0);

        customerRepository.save(newCustomer);

        return true;

    }

    @Transactional(readOnly = true)
    public List<UserDetailsDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(UserDetailsDto::new).collect(Collectors.toList());
    }

    public void deleteCustomer(String username) {
        customerRepository.findOneByUsername(username).ifPresent(
                customer -> customerRepository.delete(customer)
        );
    }

    public Customer getCustomer(String username) {
        customerRepository.findOneByUsername(username).ifPresent(
                UserDetailsDto::new
        );
        throw new UsernameNotFoundException("User could not be found");
    }
}
