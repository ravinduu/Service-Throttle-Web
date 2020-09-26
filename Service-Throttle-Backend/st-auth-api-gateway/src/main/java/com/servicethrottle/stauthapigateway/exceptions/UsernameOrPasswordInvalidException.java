package com.servicethrottle.stauthapigateway.exceptions;

public class UsernameOrPasswordInvalidException extends Throwable {
    public UsernameOrPasswordInvalidException(Exception e) {
        super("Username or Password Invalid "+e);
    }
}
