package com.servicethrottle.stuaaservice.exceptions;

public class InvalidActivationCodeException extends RuntimeException {
    public InvalidActivationCodeException() {
        super("Invalid activation code!");
    }
}
