package com.spring.techpractica.service;

import com.spring.techpractica.dto.ResetPasswordResponse;
import com.spring.techpractica.model.entity.ResetPassword;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {


    private static String SUBJECT_EMAIL_RESET_PASSWORD = "RESET_PASSWORD";

    private static String SUBJECT_EMAIL_SUMBIT_EMAIL = "SUBMIT_EMAIL";

    private final JavaMailSender javaMailSender;

    private final ResetPasswordService resetPasswordService;

    public MailSender(JavaMailSender javaMailSender,
                      ResetPasswordService resetPasswordService) {
        this.javaMailSender = javaMailSender;
        this.resetPasswordService = resetPasswordService;
    }

    /*
    send Email
    */
    public void sendResetPassword(ResetPasswordResponse resetPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
    }
}
