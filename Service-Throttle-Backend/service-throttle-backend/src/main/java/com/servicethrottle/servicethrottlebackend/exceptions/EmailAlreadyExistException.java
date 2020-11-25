package com.servicethrottle.servicethrottlebackend.exceptions;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(){
        super("email already used !!");
    }
}
