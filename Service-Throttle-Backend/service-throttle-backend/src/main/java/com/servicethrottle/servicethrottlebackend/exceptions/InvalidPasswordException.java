package com.servicethrottle.servicethrottlebackend.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super("Invalid Password !!");
    }
}
