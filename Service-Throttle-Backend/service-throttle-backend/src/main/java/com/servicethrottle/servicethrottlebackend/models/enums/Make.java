package com.servicethrottle.servicethrottlebackend.models.enums;

/**
 Enum contain vehicle brands (make) available
 */

public enum Make {
    ALFA_ROMERO("alfa romero");

    private String make;

    private Make(String make){
        this.make = make;
    }
}
