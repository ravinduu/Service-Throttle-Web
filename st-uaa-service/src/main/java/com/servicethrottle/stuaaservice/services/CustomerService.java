package com.servicethrottle.stuaaservice.services;

import com.servicethrottle.stuaaservice.configurations.SecurityConfiguration;
import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.repositories.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CustomerService {

    final
    CustomerRepository customerRepository;

    final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer registerUser(RegistrationRequest registrationRequest) {
        Customer customer = new Customer();
        customer.setcUsername(registrationRequest.getcUsername());
        customer.setcEmail(registrationRequest.getcEmail());
        customer.setcPassword(encodePassword(registrationRequest.getcPassword()));
        customer.setActivated(false);
        customer.setCreated(Instant.now());

        customerRepository.save(customer);
        return customer;
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
