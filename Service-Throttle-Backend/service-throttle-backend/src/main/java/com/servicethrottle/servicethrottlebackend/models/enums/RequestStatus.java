package com.servicethrottle.servicethrottlebackend.models.enums;

public enum RequestStatus {

    IN_PROGRESS("inProgress"),
    PROCESSING("processing"),
    COMPLETED("completed");

    private String status;

    RequestStatus(String status) {
        this.status = status;
    }
}
