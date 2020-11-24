package com.servicethrottle.servicethrottlebackend.models.enums;

public enum AccountType {
    ADMIN_ACCOUNT("adminAccount"),
    SUPERVISOR_ACCOUNT("supervisorAccount"),
    MOBILE_MECHANIC_ACCOUNT("mobileMechanicAccount"),
    CUSTOMER_ACCOUNT("customerAccount");

    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
