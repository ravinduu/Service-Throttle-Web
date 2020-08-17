package com.servicethrottle.stuaaservice.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditRequest {
    private long custId;

    @NotNull
    @Size(min = 5, max = 100)
    private String custUsername;

    @Size(max = 50)
    private String custFirstName;

    @Size(max = 50)
    private String custLastName;

    @Size(min = 9, max = 12)
    private String custPhoneNumber;

    @Size(max = 254)
    private String custAddress;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    private String custEmail;

    public EditRequest(Long custId,@NotNull @Size(min = 5, max = 100) String custUsername, @Size(max = 50) String custFirstName, @Size(max = 50) String custLastName, @Size(min = 9, max = 12) String custPhoneNumber, @Size(max = 254) String custAddress, @NotNull @Email @Size(min = 5, max = 254) String custEmail) {
        this.custId = custId;
        this.custUsername = custUsername;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custPhoneNumber = custPhoneNumber;
        this.custAddress = custAddress;
        this.custEmail = custEmail;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
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

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    @Override
    public String toString() {
        return "EditRequest{" +
                "custId=" + custId +
                ", custUsername='" + custUsername + '\'' +
                ", custFirstName='" + custFirstName + '\'' +
                ", custLastName='" + custLastName + '\'' +
                ", custPhoneNumber='" + custPhoneNumber + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custEmail='" + custEmail + '\'' +
                '}';
    }
}
