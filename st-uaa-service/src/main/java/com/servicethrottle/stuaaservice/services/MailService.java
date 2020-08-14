package com.servicethrottle.stuaaservice.services;

import com.servicethrottle.stuaaservice.exceptions.MailSendingFailException;
import com.servicethrottle.stuaaservice.models.ActivationCode;
import com.servicethrottle.stuaaservice.models.PasswordResetKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${language.key}")
    private String language;

    @Value("${ServiceThrottle.Email")
    private String serviceThrottleEmail;


    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendActivationEmail(ActivationCode activationCode) {
        sendEmail(activationCode.getCustomer().getCustEmail(),
                "Service Throttle account Activation Email",
                "please click on the below url to activate your account :" + " path/"+activationCode.getActivationCode());
    }

    public void sendPasswordResetRequestEmail(PasswordResetKey passwordResetKey) {
        sendEmail(passwordResetKey.getCustomer().getCustEmail(),
                "Service Throttle Password Reset Key",
                "Use this Key to reset Your password : " + passwordResetKey.getResetKey());
    }

    private void sendEmail(String recipient, String subject, String body) {
        if(recipient.equals(null)) return;
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(serviceThrottleEmail);
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
        };
        try{
            mailSender.send(mimeMessagePreparator);
        }
        catch (MailException e){
            throw new MailSendingFailException("Exception occurred when sending mail to " +
                    recipient, e);
        }

    }

}
