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
    private long cId;

    @Size(max = 50)
    @Column(length = 50)
    private String cFirstName;

    @Size(max = 50)
    @Column(length = 50)
    private String cLastName;

    @Size(min = 5, max = 254)
    @Column(length = 254, unique = true)
    private String cPhoneNumber;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String cUsername;

    @JsonIgnore
    @NotNull
    @Size(min = 8)
    @Column(nullable = false)
    private String cPassword;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, nullable = false, unique = true)
    private String cEmail;

    @Size(max = 254)
    @Column(length = 254)
    private String cAddress;

    private Instant created;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public String getcFirstName() {
        return cFirstName;
    }

    public void setcFirstName(String cFirstName) {
        this.cFirstName = cFirstName;
    }

    public String getcLastName() {
        return cLastName;
    }

    public void setcLastName(String cLastName) {
        this.cLastName = cLastName;
    }

    public String getcPhoneNumber() {
        return cPhoneNumber;
    }

    public void setcPhoneNumber(String cPhoneNumber) {
        this.cPhoneNumber = cPhoneNumber;
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

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
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
                "cId=" + cId +
                ", cFirstName='" + cFirstName + '\'' +
                ", cLastName='" + cLastName + '\'' +
                ", cPhoneNumber='" + cPhoneNumber + '\'' +
                ", cUsername='" + cUsername + '\'' +
                ", cPassword='" + cPassword + '\'' +
                ", cEmail='" + cEmail + '\'' +
                ", cAddress='" + cAddress + '\'' +
                ", created=" + created +
                ", activated=" + activated +
                '}';
    }
}
