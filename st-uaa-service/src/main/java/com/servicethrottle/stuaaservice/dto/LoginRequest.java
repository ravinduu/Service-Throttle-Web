package com.servicethrottle.stuaaservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotNull
    @Size(min = 5, max = 100)
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;

    public LoginRequest(@NotNull @Size(min = 1, max = 50) String username, @NotNull @Size(min = 4, max = 100) String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
