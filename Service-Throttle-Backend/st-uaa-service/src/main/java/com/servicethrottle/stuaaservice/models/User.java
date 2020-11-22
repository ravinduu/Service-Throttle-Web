package com.servicethrottle.stuaaservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
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
    private Set<UserRole> authorities = new HashSet<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
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

    public Set<UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                activated == user.activated &&
                Objects.equals(userFirstName, user.userFirstName) &&
                Objects.equals(userLastName, user.userLastName) &&
                Objects.equals(userPhoneNumber, user.userPhoneNumber) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userAddress, user.userAddress) &&
                Objects.equals(created, user.created) &&
                Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userFirstName, userLastName, userPhoneNumber, username, password, userEmail, userAddress, created, activated, authorities);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", created=" + created +
                ", activated=" + activated +
                ", authorities=" + authorities +
                '}';
    }
}
