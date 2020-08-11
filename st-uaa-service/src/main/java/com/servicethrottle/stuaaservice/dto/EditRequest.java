package com.servicethrottle.stuaaservice.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditRequest {

    @Size(max = 50)
    private String custFirstName;

    @Size(max = 50)
    private String custLastName;

    @Size(min = 9, max = 12)
    private String custPhoneNumber;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    private String custEmail;

    @Size(max = 254)
    @Column(length = 254)
    private String custAddress;

    public EditRequest(String custFirstName,
                       String custLastName,
                       String custPhoneNumber,
                       String custEmail,
                       String custAddress) {
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custPhoneNumber = custPhoneNumber;
        this.custEmail = custEmail;
        this.custAddress = custAddress;
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


    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "EditRequest{" +
                "custFirstName='" + custFirstName + '\'' +
                ", custLastName='" + custLastName + '\'' +
                ", custPhoneNumber='" + custPhoneNumber + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}