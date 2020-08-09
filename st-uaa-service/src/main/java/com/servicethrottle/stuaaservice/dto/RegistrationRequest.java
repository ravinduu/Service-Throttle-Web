package com.servicethrottle.stuaaservice.dto;

public class RegistrationRequest {
    private String cEmail;
    private String cUsername;
    private String cPassword;

    public RegistrationRequest(String cEmail, String cUsername, String cPassword) {
        this.cEmail = cEmail;
        this.cUsername = cUsername;
        this.cPassword = cPassword;
    }

    public RegistrationRequest() {
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "cEmail='" + cEmail + '\'' +
                ", cUsername='" + cUsername + '\'' +
                ", cPassword='" + cPassword + '\'' +
                '}';
    }
}
