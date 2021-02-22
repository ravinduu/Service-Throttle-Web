package com.servicethrottle.servicethrottlebackend.models.enums;

/**
 Enum contain vehicle engine available
 */

public enum Engine {
    _29L_V6_TURBO("2.9L V6 TURBO"),
    _29L_L4_TURBO("2.9L L4 TURBO"),
    I_DONT_KNOW("I dont know");

    private String engine;

    Engine(String engine) {
        this.engine = engine;
    }
}
