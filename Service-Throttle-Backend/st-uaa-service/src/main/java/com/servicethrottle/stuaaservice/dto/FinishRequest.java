package com.servicethrottle.stuaaservice.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FinishRequest {

    @NotNull
    @Size(min = 5, max = 100)
    private String custUsername;

    @NotNull
    @Size(max = 50)
    private String custFirstName;

    @NotNull
    @Size(max = 50)
    private String custLastName;

    @NotNull
    @Size(min = 9, max = 12)
    private String custPhoneNumber;

    @NotNull
    @Size(max = 254)
    private String custAddress;

    public FinishRequest(@NotNull @Size(min = 5, max = 100) String custUsername,
                         @NotNull @Size(max = 50) String custFirstName,
                         @NotNull @Size(max = 50) String custLastName,
                         @NotNull @Size(min = 9, max = 12) String custPhoneNumber,
                         @NotNull @Size(max = 254) String custAddress) {
        this.custUsername = custUsername;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custPhoneNumber = custPhoneNumber;
        this.custAddress = custAddress;
    }

    public String getCustUsername() {
        return custUsername;
    }

    public void setCustUsername(String custUsername) {
        this.custUsername = custUsername;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustPhoneNumber() {
        return custPhoneNumber;
    }

    public void setCustPhoneNumber(String custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "FinishRequest{" +
                "custUsername='" + custUsername + '\'' +
                ", custFirstName='" + custFirstName + '\'' +
                ", custLastName='" + custLastName + '\'' +
                ", custPhoneNumber='" + custPhoneNumber + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}