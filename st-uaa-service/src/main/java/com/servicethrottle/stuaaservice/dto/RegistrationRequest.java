package com.servicethrottle.stuaaservice.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationRequest {

    @NotNull
    @Size(min = 5, max = 100)
    private String custUsername;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    private String custEmail;

    @NotNull
    @Size(min = 8)
    private String custPassword;

    public RegistrationRequest(String custEmail, String custUsername, String custPassword) {
        this.custEmail = custEmail;
        this.custUsername = custUsername;
        this.custPassword = custPassword;
    }

    public String getCustUsername() {
        return custUsername;
    }

    public void setCustUsername(String custUsername) {
        this.custUsername = custUsername;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }
}
