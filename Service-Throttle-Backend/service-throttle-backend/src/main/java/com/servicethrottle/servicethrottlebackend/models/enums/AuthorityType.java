package com.servicethrottle.servicethrottlebackend.models.enums;

public enum AuthorityType {
    ADMIN("admin"),
    SUPERVISOR("supervisor"),
    MOBILE_MECHANIC("mobileMechanic"),
    CUSTOMER("customer");

    private final String authorityType;

    AuthorityType(String authorityType) {
        this.authorityType = authorityType;
    }

    public String getAuthorityType() {
        return authorityType;
    }
}
