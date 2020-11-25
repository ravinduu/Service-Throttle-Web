package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.EmailAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public int registerCustomer(String username, String email) {
        customerRepository.findOneByEmail(email).ifPresent(
                customerExist -> {
                    throw new EmailAlreadyExistException();
                }
        );

        Customer newCustomer = new Customer();
        newCustomer.setUsername(username);
        newCustomer.setEmail(email);
//        newCustomer.setActivated(false);
        newCustomer.setCreated(Instant.now());
        newCustomer.setLoyaltyPoints(0);
        customerRepository.save(newCustomer);

        return 1;

    }
}
