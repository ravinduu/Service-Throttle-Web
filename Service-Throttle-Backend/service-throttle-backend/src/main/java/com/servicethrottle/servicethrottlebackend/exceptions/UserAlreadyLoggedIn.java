package com.servicethrottle.servicethrottlebackend.exceptions;

import javax.security.auth.login.LoginException;

public class UserAlreadyLoggedIn extends LoginException {
    public UserAlreadyLoggedIn(String already_logged_in) {
        super(already_logged_in);
    }
}
