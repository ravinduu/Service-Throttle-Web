package com.servicethrottle.stuaaservice.exceptions;

public class UsernameOrPasswordInvalidException extends Exception {
    public UsernameOrPasswordInvalidException(Exception e) {
        super("Username or Password Invalid "+e);
    }
}
