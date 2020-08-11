package com.servicethrottle.stuaaservice.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String activationCode;

    @OneToOne(fetch = LAZY)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "ActivationCode{" +
                "id=" + id +
                ", activationCode='" + activationCode + '\'' +
                ", customer=" + customer +
                '}';
    }
}
