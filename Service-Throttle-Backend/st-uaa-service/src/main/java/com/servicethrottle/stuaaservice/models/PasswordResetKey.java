package com.servicethrottle.stuaaservice.models;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class PasswordResetKey {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String resetKey;

    @OneToOne(fetch = LAZY)
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "PasswordResetKey{" +
                "id=" + id +
                ", resetKey='" + resetKey + '\'' +
                ", customer=" + customer +
                '}';
    }
}
