package com.servicethrottle.stuaaservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String username;

    @JsonIgnore
    @NotNull
    @Size(min = 8)
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = LAZY)
    private Set<UserRole> authorities = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        Login login = (Login) o;
        return id == login.id &&
                Objects.equals(username, login.username) &&
                Objects.equals(password, login.password) &&
                Objects.equals(authorities, login.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, authorities);
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
