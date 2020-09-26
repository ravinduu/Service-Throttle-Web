package com.servicethrottle.stuaaservice.exceptions;

public class AccountNotActivatedException extends RuntimeException {
    public AccountNotActivatedException(){
        super("Account is Not Activated, Please Activate it");
    }

}
