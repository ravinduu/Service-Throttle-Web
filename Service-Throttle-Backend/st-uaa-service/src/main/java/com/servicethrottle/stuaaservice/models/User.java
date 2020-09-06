package com.servicethrottle.stuaaservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Size(max = 50)
    @Column(length = 50)
    private String userFirstName;

    @Size(max = 50)
    @Column(length = 50)
    private String userLastName;

    @Size(min = 9, max = 12)
    @Column(length = 12, unique = true)
    private String userPhoneNumber;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 8)
    @Column(nullable = false)
    private String password;

    @NotNull
    @Email
    @Size(min = 5, max = 254)
    @Column(length = 254, nullable = false, unique = true)
    private String userEmail;

    @Size(max = 254)
    @Column(length = 254)
    private String userAddress;

    private Instant created;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @JsonIgnore
    @ManyToMany
    @JoinTable
    private Set<Authority> authorities = new HashSet<>();



}
