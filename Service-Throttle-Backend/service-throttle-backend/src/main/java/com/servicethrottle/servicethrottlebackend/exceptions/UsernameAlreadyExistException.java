package com.servicethrottle.servicethrottlebackend.exceptions;

public class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException(){
        super("username already used !!");
    }
}
