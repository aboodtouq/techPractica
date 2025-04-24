package com.spring.techpractica.service.otp;

import com.spring.techpractica.dto.otp.UserEmailSendOtp;
import com.spring.techpractica.factory.OtpFactory;
import com.spring.techpractica.model.entity.Otp;
import com.spring.techpractica.model.entity.User;
import com.spring.techpractica.service.user.UserManagementData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOtp {

    private final UserManagementData userManagementData;

    private final OtpManagementData otpManagementData;


    public Otp createOtp(UserEmailSendOtp userEmailSendOtp) {

        User user = userManagementData.getUserByEmail(userEmailSendOtp.getUserEmail());
        Otp otp = OtpFactory.createOtpFrom(user);
        return otpManagementData.saveOtp(otp);
    }
}
