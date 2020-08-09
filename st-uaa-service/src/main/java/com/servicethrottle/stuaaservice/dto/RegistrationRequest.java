package com.servicethrottle.stuaaservice.dto;

public class RegistrationRequest {
    private String custUsername;
    private String custEmail;
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
