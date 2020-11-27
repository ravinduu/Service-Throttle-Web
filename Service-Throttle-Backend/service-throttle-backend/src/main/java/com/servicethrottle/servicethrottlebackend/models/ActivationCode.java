package com.servicethrottle.servicethrottlebackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.io.Serializable;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "st_account_activation_code")
public class ActivationCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 10)
    @Column(length =10, unique = true)
    private String activationCode;

    @OneToOne(fetch = LAZY)
    private UserCredentials userCredentials;
}
