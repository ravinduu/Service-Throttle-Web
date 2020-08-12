package com.servicethrottle.stuaaservice.services;

import com.servicethrottle.stuaaservice.dto.EditRequest;
import com.servicethrottle.stuaaservice.dto.RegistrationRequest;
import com.servicethrottle.stuaaservice.exceptions.AccountNotActivatedException;
import com.servicethrottle.stuaaservice.exceptions.InvalidActivationCodeException;
import com.servicethrottle.stuaaservice.exceptions.UsernameAlreadyUsedException;
import com.servicethrottle.stuaaservice.models.ActivationCode;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.models.Login;
import com.servicethrottle.stuaaservice.repositories.ActivationCodeRepository;
import com.servicethrottle.stuaaservice.repositories.CustomerRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final ActivationCodeRepository activationCodeRepository;

    private final LoginService loginService;

    private final MailService mailService;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, ActivationCodeRepository activationCodeRepository, LoginService loginService, MailService mailService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.activationCodeRepository = activationCodeRepository;
        this.loginService = loginService;
        this.mailService = mailService;
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
        this.generateActivationCode(customer);
        return customer;
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    private void generateActivationCode(Customer customer) {
        String code = UUID.randomUUID().toString();
        ActivationCode activationCode = new ActivationCode();
        activationCode.setActivationCode(code);
        activationCode.setCustomer(customer);
        activationCodeRepository.save(activationCode);
        //mailService.sendActivationEmail(activationCode);
    }

    private void activateAccount(ActivationCode activationCode) throws AccountNotFoundException {
        Customer customer = customerRepository
                .findOneByCustUsername(activationCode
                        .getCustomer()
                        .getCustUsername())
                        .orElseThrow(() -> new AccountNotFoundException());
        customer.setActivated(true);

        Login newLogin = new Login();
        newLogin.setPassword(customer.getCustPassword());
        newLogin.setUsername(customer.getCustUsername());
        loginService.createLogin(newLogin);

        customerRepository.save(customer);
    }

    public String verifyCode(String code) throws AccountNotFoundException {
        Optional<ActivationCode> activationCode = activationCodeRepository.findByActivationCode(code);
        String username = activationCode.get().getCustomer().getCustUsername();
        activateAccount(activationCode.orElseThrow(() -> new InvalidActivationCodeException()));
        activationCode.ifPresent(activationCode1 -> activationCodeRepository.deleteById(activationCode1.getId()));
        return username;
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

    public Customer getCustomer(String username) throws AccountNotFoundException {
        Customer customer = customerRepository
                .findOneByCustUsername(username)
                .orElseThrow(() -> new AccountNotFoundException());
        return customer;
    }
}
