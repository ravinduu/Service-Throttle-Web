package com.servicethrottle.servicethrottlebackend.exceptions;

public class CustomerVehicleNotFound extends RuntimeException {
    public CustomerVehicleNotFound(String s) {
        super(s +" not found!!");
    }
}
