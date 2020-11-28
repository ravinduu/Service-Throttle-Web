package com.servicethrottle.servicethrottlebackend.models.dto;

import com.servicethrottle.servicethrottlebackend.models.Admin;
import com.servicethrottle.servicethrottlebackend.models.Customer;
import com.servicethrottle.servicethrottlebackend.models.MobileMechanic;
import com.servicethrottle.servicethrottlebackend.models.Supervisor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {

    private long id;

    @Size(max = 50)
    private String firstname;

    @Size(max = 50)
    private String lastname;

    @Size(min = 9, max = 12)
    private String phoneNumber;

    @Size(min = 5, max = 100)
    private String username;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 254)
    private String address;

    private Instant created;

    public UserDetailsDto(Admin admin) {
        this.id = admin.getId();
        this.firstname = admin.getFirstname();
        this.lastname = admin.getLastname();
        this.phoneNumber = admin.getPhoneNumber();
        this.username = admin.getUsername() ;
        this.email = admin.getEmail() ;
        this.address = admin.getAddress();
        this.created = admin.getCreated();
    }

    public UserDetailsDto(Customer customer) {
        this.id = customer.getId();
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.phoneNumber = customer.getPhoneNumber();
        this.username = customer.getUsername() ;
        this.email = customer.getEmail() ;
        this.address = customer.getAddress();
        this.created = customer.getCreated();
    }

    public UserDetailsDto(Supervisor supervisor) {
        this.id = supervisor.getId();
        this.firstname = supervisor.getFirstname();
        this.lastname = supervisor.getLastname();
        this.phoneNumber = supervisor.getPhoneNumber();
        this.username = supervisor.getUsername() ;
        this.email = supervisor.getEmail() ;
        this.address = supervisor.getAddress();
        this.created = supervisor.getCreated();
    }

    public UserDetailsDto(MobileMechanic mobileMechanic) {
        this.id = mobileMechanic.getId();
        this.firstname = mobileMechanic.getFirstname();
        this.lastname = mobileMechanic.getLastname();
        this.phoneNumber = mobileMechanic.getPhoneNumber();
        this.username = mobileMechanic.getUsername() ;
        this.email = mobileMechanic.getEmail() ;
        this.address = mobileMechanic.getAddress();
        this.created = mobileMechanic.getCreated();
    }
}
