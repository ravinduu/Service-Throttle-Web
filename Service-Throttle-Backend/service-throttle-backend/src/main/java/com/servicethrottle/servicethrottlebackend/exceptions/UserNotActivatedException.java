package com.servicethrottle.servicethrottlebackend.exceptions;

import javax.security.auth.login.LoginException;
import javax.security.sasl.AuthenticationException;

public class UserNotActivatedException extends RuntimeException {
    public UserNotActivatedException(){
        super("user is not activated !!");
    }

}
