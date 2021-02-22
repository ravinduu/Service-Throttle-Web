package com.servicethrottle.servicethrottlebackend.exceptions;

public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException(String s) {
        super(s);
    }
}
