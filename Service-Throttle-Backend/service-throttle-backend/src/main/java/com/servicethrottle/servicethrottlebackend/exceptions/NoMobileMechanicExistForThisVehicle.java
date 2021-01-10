package com.servicethrottle.servicethrottlebackend.exceptions;

public class NoMobileMechanicExistForThisVehicle extends RuntimeException {
    public NoMobileMechanicExistForThisVehicle(String s) {
        super(s);
    }
}
