package com.servicethrottle.stuaaservice.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long custId;

    @Size(max = 50)
    @Column(length = 50)
    private String custFirstName;

    @Size(max = 50)
    @Column(length = 50)
    private String custLastName;

    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String custPhoneNumber;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String custUsername;

    @JsonIgnore
    @NotNull
    @Size(min = 8)
    @Column(nullable = false)
    private String custPassword;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, nullable = false, unique = true)
    private String custEmail;

    @Size(max = 254)
    @Column(length = 254)
    private String custAddress;

    private Instant created;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
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

    public String getCustUsername() {
        return custUsername;
    }

    public void setCustUsername(String custUsername) {
        this.custUsername = custUsername;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
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

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custFirstName='" + custFirstName + '\'' +
                ", custLastName='" + custLastName + '\'' +
                ", custPhoneNumber='" + custPhoneNumber + '\'' +
                ", custUsername='" + custUsername + '\'' +
                ", custPassword='" + custPassword + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", created=" + created +
                ", activated=" + activated +
                '}';
    }
}
