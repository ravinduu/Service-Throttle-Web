package com.servicethrottle.stuaaservice.services;

import com.servicethrottle.stuaaservice.configurations.SecurityConfiguration;
import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.exceptions.UsernameAlreadyUsedException;
import com.servicethrottle.stuaaservice.models.ActivationCode;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.repositories.ActivationCodeRepository;
import com.servicethrottle.stuaaservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class CustomerService {

    final
    CustomerRepository customerRepository;

    final PasswordEncoder passwordEncoder;

    final
    ActivationCodeRepository activationCodeRepository;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, ActivationCodeRepository activationCodeRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.activationCodeRepository = activationCodeRepository;
    }

    public Customer registerUser(RegistrationRequest registrationRequest) {
        customerRepository.findOneByCustUsername(registrationRequest.getCustUsername()).ifPresent(customer -> {
            if (customer.getCustUsername().equals(registrationRequest.getCustUsername())){
                throw new UsernameAlreadyUsedException();
            }
        });

        Customer customer = new Customer();
        customer.setCustUsername(registrationRequest.getCustUsername());
        customer.setCustEmail(registrationRequest.getCustEmail());
        customer.setCustPassword(encodePassword(registrationRequest.getCustPassword()));
        customer.setActivated(false);
        customer.setCreated(Instant.now());
        customerRepository.save(customer);

        String activationCode = this.generateActivationCode(customer);// send this from mail;
        return customer;
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    private String generateActivationCode(Customer customer) {
        String code = UUID.randomUUID().toString();
        ActivationCode activationCode = new ActivationCode();
        activationCode.setActivationCode(code);
        activationCode.setCustomer(customer);
        activationCodeRepository.save(activationCode);
        return code;

    }
}
