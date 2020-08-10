package com.servicethrottle.stuaaservice.exceptions;

public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException() {
        super("Username already used!");
    }
}
