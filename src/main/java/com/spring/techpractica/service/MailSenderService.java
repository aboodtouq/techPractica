package com.spring.techpractica.service;

import com.spring.techpractica.dto.restpassword.ResetPasswordResponse;
import com.spring.techpractica.exception.ResourcesNotFoundException;
import com.spring.techpractica.model.entity.ResetPassword;
import com.spring.techpractica.repository.ResetPasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailSenderService {


    private static String SUBJECT_EMAIL_RESET_PASSWORD = "RESET_PASSWORD";

    private static String SUBJECT_EMAIL_SUMBIT_EMAIL = "SUBMIT_EMAIL";

    private final JavaMailSender javaMailSender;


    private final ResetPasswordRepository resetPasswordRepository;


    public void sendResetPassword(ResetPasswordResponse resetPasswordResponse) {

        SimpleMailMessage message = new SimpleMailMessage();

        ResetPassword resetPassword = resetPasswordRepository
                .getResetPasswordByResetPasswordId(resetPasswordResponse.getResetId()).
                orElseThrow(() -> new ResourcesNotFoundException("Not found reset password"));

        message.setTo(resetPasswordResponse.getUserEmail());
        message.setSubject(SUBJECT_EMAIL_RESET_PASSWORD);

        message.setText("This is your Otp " + resetPassword.getOtpCode() + "\n\n" + "the code will expire in 5 minutes.");
        javaMailSender.send(message);
    }
}
