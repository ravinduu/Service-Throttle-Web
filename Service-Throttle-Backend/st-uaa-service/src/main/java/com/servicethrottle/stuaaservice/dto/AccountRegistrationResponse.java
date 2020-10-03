package com.servicethrottle.stuaaservice.dto;

import com.servicethrottle.stuaaservice.models.ActivationCode;

public class AccountRegistrationResponse {
    private AuthenticationResponse authenticationResponse;
    private String activationCode;

    public AccountRegistrationResponse(AuthenticationResponse authenticationResponse, String activationCode) {
        this.authenticationResponse = authenticationResponse;
        this.activationCode = activationCode;
    }

    public AuthenticationResponse getAuthenticationResponse() {
        return authenticationResponse;
    }

    public void setAuthenticationResponse(AuthenticationResponse authenticationResponse) {
        this.authenticationResponse = authenticationResponse;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public String toString() {
        return "AccountRegistrationResponse{" +
                "authenticationResponse=" + authenticationResponse +
                ", activationCode='" + activationCode + '\'' +
                '}';
    }
}
