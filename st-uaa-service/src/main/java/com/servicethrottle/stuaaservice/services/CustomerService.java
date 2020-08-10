package com.servicethrottle.stuaaservice.services;

import com.servicethrottle.stuaaservice.dto.EditRequest;
import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.exceptions.AccountNotActivatedException;
import com.servicethrottle.stuaaservice.exceptions.InvalidActivationCodeException;
import com.servicethrottle.stuaaservice.exceptions.UsernameAlreadyUsedException;
import com.servicethrottle.stuaaservice.models.ActivationCode;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.repositories.ActivationCodeRepository;
import com.servicethrottle.stuaaservice.repositories.CustomerRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
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

    private void activateAccount(ActivationCode activationCode) throws AccountNotFoundException {
        Customer customer = customerRepository
                .findOneByCustUsername(activationCode
                        .getCustomer()
                        .getCustUsername())
                        .orElseThrow(() -> new AccountNotFoundException());
        customer.setActivated(true);
        customerRepository.save(customer);
    }

    public void verifyCode(String code) throws AccountNotFoundException {
        Optional<ActivationCode> activationCode = activationCodeRepository.findByActivationCode(code);
        activateAccount(activationCode.orElseThrow(() -> new InvalidActivationCodeException()));
    }

    public void editAccount(EditRequest editRequest) throws AccountNotFoundException {
        Customer customer = customerRepository.findOneByCustEmail(editRequest
                .getCustEmail())
                .orElseThrow(() -> new AccountNotFoundException());
        if (!customer.isActivated()) throw new AccountNotActivatedException();
        else {
            customer.setCustFirstName(editRequest.getCustFirstName());
            customer.setCustLastName(editRequest.getCustLastName());
            customer.setCustPhoneNumber(editRequest.getCustPhoneNumber());
            customer.setCustAddress(editRequest.getCustAddress());
            customerRepository.save(customer);
        }
    }
}
