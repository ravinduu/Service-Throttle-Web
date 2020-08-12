package com.servicethrottle.stuaaservice.exceptions;

import org.springframework.mail.MailException;

public class MailSendingFailException extends RuntimeException {
    public MailSendingFailException(String error, MailException mailException) {
    }
}
