package com.servicethrottle.servicethrottlebackend.exceptions;

import javax.security.sasl.AuthenticationException;

public class UserNotActivatedException extends AuthenticationException {
    public UserNotActivatedException(){
        super("user is not activated !!");
    }

    public UserNotActivatedException(String message, Throwable t) {
        super(message, t);
    }

}
