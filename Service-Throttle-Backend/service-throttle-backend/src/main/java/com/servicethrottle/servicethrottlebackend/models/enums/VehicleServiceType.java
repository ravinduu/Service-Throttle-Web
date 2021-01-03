package com.servicethrottle.servicethrottlebackend.models.enums;

public enum VehicleServiceType {
    REPAIR("repair"),
    MAINTENANCE("maintenance");

    private String vehicleServiceType;

    VehicleServiceType(String vehicleServiceType) {
        this.vehicleServiceType = vehicleServiceType;
    }
}
