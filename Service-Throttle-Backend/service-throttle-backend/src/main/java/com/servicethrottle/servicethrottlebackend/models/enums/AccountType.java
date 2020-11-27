package com.servicethrottle.servicethrottlebackend.models.enums;

public enum AccountType {
    ADMIN_ACCOUNT("admin_account"),
    SUPERVISOR_ACCOUNT("supervisor_account"),
    MOBILE_MECHANIC_ACCOUNT("mobile_mechanic_account"),
    CUSTOMER_ACCOUNT("customer_account");

    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
