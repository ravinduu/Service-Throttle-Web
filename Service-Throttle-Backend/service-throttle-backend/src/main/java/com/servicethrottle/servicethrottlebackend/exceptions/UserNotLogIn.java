package com.servicethrottle.servicethrottlebackend.exceptions;

public class UserNotLogIn extends RuntimeException {
    public UserNotLogIn(String user_not_login) {
        super(user_not_login);
    }
}
