package com.servicethrottle.servicethrottlebackend.services;

import com.servicethrottle.servicethrottlebackend.exceptions.AccountResourceException;
import com.servicethrottle.servicethrottlebackend.exceptions.InvalidPasswordException;
import com.servicethrottle.servicethrottlebackend.exceptions.UserAlreadyLockedException;
import com.servicethrottle.servicethrottlebackend.exceptions.UsernameAlreadyExistException;
import com.servicethrottle.servicethrottlebackend.models.ActivationCode;
import com.servicethrottle.servicethrottlebackend.models.Authority;
import com.servicethrottle.servicethrottlebackend.models.UserCredentials;
import com.servicethrottle.servicethrottlebackend.models.dto.PasswordResetDto;
import com.servicethrottle.servicethrottlebackend.models.dto.RegistrationRequestDto;
import com.servicethrottle.servicethrottlebackend.models.dto.UserDetailsDto;
import com.servicethrottle.servicethrottlebackend.repositories.ActivationCodeRepository;
import com.servicethrottle.servicethrottlebackend.repositories.UserCredentialsRepository;
import com.servicethrottle.servicethrottlebackend.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static com.servicethrottle.servicethrottlebackend.models.enums.AccountType.*;
import static com.servicethrottle.servicethrottlebackend.models.enums.AuthorityType.*;

@Service
@AllArgsConstructor
@Transactional
public class UserAccountService {

    private final UserCredentialsRepository userCredentialsRepository;
    private final ActivationCodeRepository activationCodeRepository ;

    private final AdminService adminService;
    private final AuthorityService authorityService;
    private final CustomerService customerService;
    private final SupervisorService supervisorService;
    private final MobileMechanicService mobileMechanicService;
    private final MailService mailService;

    private final PasswordEncoder passwordEncoder;


    public String registerUser(RegistrationRequestDto registrationRequestDto) throws Exception {
        boolean success = false;

        String username = registrationRequestDto.getUsername();
        String email = registrationRequestDto.getEmail();
        String encodedPassword = encodePassword(registrationRequestDto.getPassword());
        String accountType = registrationRequestDto.getAccountType();

        if (username.equals("") || email.equals("") || encodedPassword.equals("")) return "Error";

        if(isUniqueUsername(username)){

            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setUsername(username);
            userCredentials.setPassword(encodedPassword);
            Set<Authority> userAuthority = userCredentials.getAuthorities();

            if (accountType.equals(SUPERVISOR_ACCOUNT.getAccountType())){
                userCredentials.setAccountType(SUPERVISOR_ACCOUNT.getAccountType());
                userAuthority.add(authorityService.createAuthorityIfNotFound(MOBILE_MECHANIC.getAuthorityType()));
                success = supervisorService.registerSupervisor(username, email);
            }
            else if (accountType.equals(CUSTOMER_ACCOUNT.getAccountType())){
                userCredentials.setAccountType(CUSTOMER_ACCOUNT.getAccountType());
                userAuthority.add(authorityService.createAuthorityIfNotFound(CUSTOMER.getAuthorityType()));
                success = customerService.registerCustomer(username, email);
            }
            else if (accountType.equals(MOBILE_MECHANIC_ACCOUNT.getAccountType())){
                userCredentials.setLocked(true);
                userCredentials.setAccountType(MOBILE_MECHANIC_ACCOUNT.getAccountType());
                userAuthority.add(authorityService.createAuthorityIfNotFound(MOBILE_MECHANIC.getAuthorityType()));
                success = mobileMechanicService.registerMobileMechanic(username, email, true);
            }

//        userCredentials.setActivated(false);
            if (!success) return "Something goes wrong !!";

            String activationCode = generateActivationCode(userCredentials).getActivationCode();

            String to = registrationRequestDto.getEmail();
            String subject = activationCode+" is your Service Throttle code";
            String body = "Hi,\n\nSomeone tried to sign up for a Service Throttle account with "+to+". If it was you, enter this activation code in the app: \n\t<p1>" + activationCode+"</p>\n\nThank you,\nService Throttle";

//            mailService.sendMails(to, subject, body);

            userCredentialsRepository.save(userCredentials);
            return "Success";
        }
        return "Something goes wrong !!";
    }


    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    private ActivationCode generateActivationCode(UserCredentials userCredentials) {
        Random random = new Random();

        String code = String.format("%04d", random.nextInt(10000));

        while (activationCodeRepository.findOneByActivationCode(code).isPresent()){
            code = String.format("%04d", random.nextInt(10000));
        }

//        activation code and customer details save in ActivationCode table
        ActivationCode activationCode = new ActivationCode();
        activationCode.setActivationCode(code);
        activationCode.setUserCredentials(userCredentials);
        activationCodeRepository.save(activationCode);

        return activationCode;
    }

    public String activateUser(String code) throws AccountResourceException {

        Optional<ActivationCode> activationCode = activationCodeRepository.findOneByActivationCode(code);

        if (!activationCode.isPresent()) {
            throw new AccountResourceException("No user was found for this activation code");
        }

        activationCode.map(
                activationCode1 -> {
                    userCredentialsRepository.findById(activationCode.get().getUserCredentials().getId()).map(
                            userCredentials -> {
                                userCredentials.setActivated(true);
                                userCredentials.setLocked(false);
                                userCredentialsRepository.save(userCredentials);
                                activationCodeRepository.delete(activationCode1);
                                return "Activated";
                            }
                    );
                    return "No user was found for this activation code";
                }
        );
        return "Activated";
    }

    public String registerAdmin(RegistrationRequestDto registrationRequestDto) {
        boolean success = false;

        String username = registrationRequestDto.getUsername();
        String email = registrationRequestDto.getEmail();
        String encodedPassword = encodePassword(registrationRequestDto.getPassword());
        String accountType = registrationRequestDto.getAccountType();

        if(isUniqueUsername(username)){
            UserCredentials userCredentials = new UserCredentials();
            userCredentials.setUsername(username);
            userCredentials.setPassword(encodedPassword);
            userCredentials.setActivated(true);
            userCredentials.setLocked(false);
            Set<Authority> userAuthority = userCredentials.getAuthorities();

            userCredentials.setAccountType(ADMIN_ACCOUNT.getAccountType());
            userAuthority.add(authorityService.createAuthorityIfNotFound(ADMIN.getAuthorityType()));

            if(adminService.registerAdmin(username, email)){
                userCredentialsRepository.save(userCredentials);
                return "Success";
            }
        }
        return "Something goes wrong !!";
    }

    private boolean isUniqueUsername(String username){
        userCredentialsRepository.findOneByUsername(username)
                .ifPresent( userExist -> {
                            throw new UsernameAlreadyExistException();
                        }
                );

        return true;
    }


    public void deleteUser(String username) {
        userCredentialsRepository.findOneByUsername(username).ifPresent(
                userExist -> {
                    if(userExist.getAccountType().equals(ADMIN_ACCOUNT.getAccountType())) adminService.deleteAdmin(userExist.getUsername());
                    else if(userExist.getAccountType().equals(SUPERVISOR_ACCOUNT.getAccountType())) supervisorService.deleteSupervisor(userExist.getUsername());
                    else if(userExist.getAccountType().equals(MOBILE_MECHANIC_ACCOUNT.getAccountType())) mobileMechanicService.deleteMobileMechanic(userExist.getUsername());
                    else if(userExist.getAccountType().equals(CUSTOMER_ACCOUNT.getAccountType())) customerService.deleteCustomer(userExist.getUsername());

                    userCredentialsRepository.delete(userExist);
                }

        );
    }

    public String lockUser(String username) {
        userCredentialsRepository.findOneByUsername(username).ifPresent(
                userExist -> {
                    if (userExist.isLocked() == true) throw new UserAlreadyLockedException("User Already Locked");
                    userExist.setLocked(true);
                    userCredentialsRepository.save(userExist);
                }
        );
        return "User "+username+" have locked !!";
    }

    public String unlockUser(String username) {
        userCredentialsRepository.findOneByUsername(username).ifPresent(
                userExist -> {
                    if (userExist.isLocked() == false) throw new UserAlreadyLockedException("User Already Unlocked");
                    userExist.setLocked(false);
                    userCredentialsRepository.save(userExist);
                }
        );
        return "User "+username+" have unlocked !!";
    }


    @Transactional(readOnly = true)
    public UserDetailsDto getUser(String username) {



        UserDetailsDto userDetailsDto = new UserDetailsDto();
//        String username = SecurityUtils.getCurrentUsername().orElseThrow(() -> new UsernameNotFoundException("User  was not found !!"));

        UserCredentials userCredentials = userCredentialsRepository.findOneByUsername(username).get();

        if (userCredentials.getAccountType().equals(ADMIN_ACCOUNT.getAccountType())) userDetailsDto = adminService.getAdmin(username);
        else if (userCredentials.getAccountType().equals(SUPERVISOR_ACCOUNT.getAccountType())) userDetailsDto = supervisorService.getSupervisor(username);
        else if (userCredentials.getAccountType().equals(MOBILE_MECHANIC_ACCOUNT.getAccountType())) userDetailsDto = new UserDetailsDto(mobileMechanicService.getMobileMechanic(username));
        else if (userCredentials.getAccountType().equals(CUSTOMER_ACCOUNT.getAccountType())) userDetailsDto = new UserDetailsDto(customerService.getCustomer(username));
        return userDetailsDto;

    }

    public String updateUser(UserDetailsDto userDetailsDto) throws AccountResourceException {
        String username = SecurityUtils.getCurrentUsername().orElseThrow(() -> new UsernameNotFoundException("User  was not found !!"));
        return updateAndSaveUser(userDetailsDto, username);
    }

    public String updateExistingUser(UserDetailsDto userDetailsDto) throws AccountResourceException {
        return updateAndSaveUser(userDetailsDto, userDetailsDto.getUsername());
    }

    private String updateAndSaveUser(UserDetailsDto userDetailsDto, String username) throws AccountResourceException {

        Optional<UserCredentials> userCredentials = userCredentialsRepository.findOneByUsername(username);
        if (!userCredentials.isPresent()) throw new AccountResourceException("User could not be found");

        userCredentials.map(userExist -> {
            if (userExist.getAccountType().equals(ADMIN_ACCOUNT.getAccountType())) adminService.updateAdmin(userDetailsDto);
            else if (userExist.getAccountType().equals(SUPERVISOR_ACCOUNT.getAccountType())) supervisorService.updateSupervisor(userDetailsDto);
            else if (userExist.getAccountType().equals(MOBILE_MECHANIC_ACCOUNT.getAccountType())) mobileMechanicService.updateMobileMechanic(userDetailsDto);
            else if (userExist.getAccountType().equals(CUSTOMER_ACCOUNT.getAccountType())) customerService.updateCustomer(userDetailsDto);

            return "User Details Updated Successfully";
        });

        return "Something Went Wrong !!";
    }

    public String resetPassword(PasswordResetDto passwordResetDto) {
       SecurityUtils.getCurrentUsername()
               .flatMap(userCredentialsRepository::findOneByUsername)
               .map(user -> {
                   String currentPassword = user.getPassword();
                   if (!passwordEncoder.matches(passwordResetDto.getOldPassword(),currentPassword)){
                       throw new InvalidPasswordException();
                   }
                   user.setPassword(passwordEncoder.encode(passwordResetDto.getNewPassword()));
                   currentPassword = null;
                   return "password change successfully ";
               });
       return "Something went wrong !!";
    }

}
