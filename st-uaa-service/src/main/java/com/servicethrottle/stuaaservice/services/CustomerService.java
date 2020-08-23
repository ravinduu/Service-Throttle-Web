package com.servicethrottle.stuaaservice.services;

import com.servicethrottle.stuaaservice.dto.*;
import com.servicethrottle.stuaaservice.exceptions.*;
import com.servicethrottle.stuaaservice.models.ActivationCode;
import com.servicethrottle.stuaaservice.models.Customer;
import com.servicethrottle.stuaaservice.models.Login;
import com.servicethrottle.stuaaservice.models.PasswordResetKey;
import com.servicethrottle.stuaaservice.repositories.ActivationCodeRepository;
import com.servicethrottle.stuaaservice.repositories.CustomerRepository;

import com.servicethrottle.stuaaservice.repositories.PasswordResetRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


//
@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final ActivationCodeRepository activationCodeRepository;

    private final LoginService loginService;

    private final MailService mailService;

    private final PasswordResetRepository passwordResetRepository;

    private final RestTemplate restTemplate;

    public CustomerService(CustomerRepository customerRepository,
                           PasswordEncoder passwordEncoder,
                           ActivationCodeRepository activationCodeRepository,
                           LoginService loginService,
                           MailService mailService,
                           PasswordResetRepository passwordResetRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.activationCodeRepository = activationCodeRepository;
        this.loginService = loginService;
        this.mailService = mailService;
        this.passwordResetRepository = passwordResetRepository;
        this.restTemplate = restTemplate;
    }



    public Customer registerUser(RegistrationRequest registrationRequest) {
        customerRepository.findOneByCustUsername(registrationRequest
                .getCustUsername())
                .ifPresent(customer -> {
//            check the username is already exists
            if (customer.getCustUsername().equals(registrationRequest.getCustUsername())){
                throw new UsernameAlreadyUsedException();
            }
        });

        Customer customer = new Customer();
        customer.setCustUsername(registrationRequest.getCustUsername());
        customer.setCustEmail(registrationRequest.getCustEmail());
//        password will be saved as encrypted
        customer.setCustPassword(encodePassword(registrationRequest.getCustPassword()));
//        new customer is not activated yet
        customer.setActivated(false);
        customer.setCreated(Instant.now());
        customerRepository.save(customer);
//        new customer get verification code
        this.generateActivationCode(customer);
        return customer;
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    private void generateActivationCode(Customer customer) {
        String code = UUID.randomUUID().toString();
//        activation code and customer details save in ActivationCode table
        ActivationCode activationCode = new ActivationCode();
        activationCode.setActivationCode(code);
        activationCode.setCustomer(customer);
        activationCodeRepository.save(activationCode);
//        verification code will send to the customer email
        //mailService.sendActivationEmail(activationCode);
    }

    private AuthenticationResponse activateAccount(ActivationCode activationCode) throws AccountNotFoundException, URISyntaxException {
        Customer customer = customerRepository
                .findOneByCustUsername(activationCode
                        .getCustomer()
                        .getCustUsername())
                        .orElseThrow(() -> new AccountNotFoundException());
//        new customer is activated
        customer.setActivated(true);

        Login newLogin = new Login();
        newLogin.setPassword(customer.getCustPassword());
        newLogin.setUsername(customer.getCustUsername());
//        save customer username and encrypted password in separate table
        loginService.createLogin(newLogin);

//        save updated user
        customerRepository.save(customer);

//        get jwt by username from gateway microservices
        AuthenticationResponse authenticationResponse = restTemplate.getForObject("http://ST-AUTH-API-GATEWAY/st/login/"+newLogin.getUsername(),AuthenticationResponse.class);

        return authenticationResponse;
    }

    public AuthenticationResponse verifyCode(String code) throws AccountNotFoundException, URISyntaxException {
        Optional<ActivationCode> activationCode = activationCodeRepository.findByActivationCode(code);
        String username = activationCode.get().getCustomer().getCustUsername();
//        check the code is correct or not
        AuthenticationResponse authenticationResponse = activateAccount(activationCode.orElseThrow(() -> new InvalidActivationCodeException()));
        activationCode.ifPresent(activationCode1 -> activationCodeRepository.deleteById(activationCode1.getId()));
        return authenticationResponse;
    }

    public void finishAccount(FinishRequest finishRequest) throws AccountNotFoundException {
        Customer customer = getCustomer(finishRequest.getCustUsername());
        if (!customer.isActivated()) throw new AccountNotActivatedException();
        else {
            customer.setCustFirstName(finishRequest.getCustFirstName());
            customer.setCustLastName(finishRequest.getCustLastName());
            customer.setCustPhoneNumber(finishRequest.getCustPhoneNumber());
            customer.setCustAddress(finishRequest.getCustAddress());
            customerRepository.save(customer);
        }
    }

    public Customer getCustomer(String username) throws AccountNotFoundException {
//        get a customer details by username
        Customer customer = customerRepository
                .findOneByCustUsername(username)
                .orElseThrow(() -> new AccountNotFoundException());
        return customer;
    }

    public List<Customer> getAll() {
//        get all customer details as a List
        return customerRepository.findAll();
    }

    public void passwordResetEmail(String custEmail) {
        System.out.println(custEmail+"Customer service");
        Customer customer = getCustomerByEmail(custEmail);
//        customer get password reset key
        String key = UUID.randomUUID().toString();
//        reset key and customer details will save at
        PasswordResetKey passwordResetKey = new PasswordResetKey();
        passwordResetKey.setResetKey(key);
        passwordResetKey.setCustomer(customer);
        passwordResetRepository.save(passwordResetKey);
//        send reset password request email
//        mailService.sendPasswordResetRequestEmail(passwordResetKey);
    }

    public void finishPasswordReset(ResetPasswordRequest resetPasswordRequest) {
//        verify the reset key
        String resetKey = resetPasswordRequest.getResetKey();
        PasswordResetKey passwordResetKey = passwordResetRepository.findOneByResetKey(resetKey)
                .orElseThrow(()-> new InvalidResetKeyException());

        String newPassword = resetPasswordRequest.getNewPassword();
        Customer customer = passwordResetKey.getCustomer();
        // reset old password to new password and save
        customer.setCustPassword(encodePassword(newPassword));
        customerRepository.save(customer);
//        remove resetKeyDetails
        passwordResetRepository.deleteById(passwordResetKey.getId());
    }


//    get customer by email
    private Customer getCustomerByEmail(String custEmail) {
        Customer customer = customerRepository.findOneByCustEmail(custEmail)
                .orElseThrow(() -> new NoAccountByThisEmailException());
        return customer;
    }

    public void deleteCustomer(String username) throws AccountNotFoundException {
//        get customer
        Customer customer = getCustomer(username);
//        delete login data of the customer
        loginService.deleteLogin(customer);
        customerRepository.delete(customer);
    }


    public void resendActivationCode(RegistrationRequest registrationRequest) throws AccountNotFoundException {
        String custUsername = registrationRequest.getCustUsername();
        Customer customer = getCustomer(custUsername);
        //find old code
        ActivationCode activationCode = activationCodeRepository.findByCustomer(customer).get(); // add custom exception <<account already activated>>
//        create new code and save it
        String newCode = UUID.randomUUID().toString();
//        activation code and customer details save in ActivationCode table
        activationCode.setActivationCode(newCode);
        activationCodeRepository.save(activationCode);
//        send new code to email
        //mailService.sendActivationEmail(activationCode);
    }

    public void editCustomer(EditRequest editRequest) throws AccountNotFoundException {
        String newUsername = editRequest.getCustUsername();
        String newEmail = editRequest.getCustEmail();
        Optional<Customer> customerExist;

//        check if the new username is already used
        customerExist = customerRepository.findOneByCustUsername(newUsername);
        if (customerExist.isPresent() && customerExist.get().getCustId() != editRequest.getCustId()){
            throw new UsernameAlreadyUsedException();
        }
//        check is the new email is already used
        customerExist = customerRepository.findOneByCustEmail(newEmail);
        if (customerExist.isPresent() && customerExist.get().getCustId() != editRequest.getCustId()){
            throw new EmailAlreadyUsedException();
        }

        String username = newUsername; //this should get by jwt auth from auth server
        Customer customer = getCustomer(username);

//        update new details
        customer.setCustUsername(editRequest.getCustUsername());
        customer.setCustFirstName(editRequest.getCustFirstName());
        customer.setCustLastName(editRequest.getCustLastName());
        customer.setCustAddress(editRequest.getCustAddress());
        customer.setCustPhoneNumber(editRequest.getCustPhoneNumber());
        customer.setCustEmail(editRequest.getCustEmail());

//        save new details
        customerRepository.save(customer);
    }


}
