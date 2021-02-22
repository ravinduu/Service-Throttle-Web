package com.servicethrottle.servicethrottlebackend.models.enums;

/**
 Enum contain vehicle models available
 */

public enum Model {
    _4C("4c"),
    GIULIA("giulia"),
    STELVIO("stelvio");

    private String model;

    Model(String model) {
        this.model = model;
    }
}
