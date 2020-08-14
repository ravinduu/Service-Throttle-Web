package com.servicethrottle.stuaaservice.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FinishRequest {

    @Size(max = 50)
    private String custFirstName;

    @Size(max = 50)
    private String custLastName;

    @Size(min = 9, max = 12)
    private String custPhoneNumber;


    @Size(max = 254)
    @Column(length = 254)
    private String custAddress;

    public FinishRequest(@Size(max = 50) String custFirstName,
                         @Size(max = 50) String custLastName,
                         @Size(min = 9, max = 12) String custPhoneNumber,
                         @Size(max = 254) String custAddress) {
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custPhoneNumber = custPhoneNumber;
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

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
}