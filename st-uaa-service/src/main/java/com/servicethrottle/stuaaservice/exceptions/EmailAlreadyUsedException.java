package com.servicethrottle.stuaaservice.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(){
        super("Email already used!");
    }
}
