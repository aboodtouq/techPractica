package com.spring.techpractica.service.otp;

import com.spring.techpractica.dto.otp.OtpResponse;
import com.spring.techpractica.dto.otp.UserEmailSendOtp;
import com.spring.techpractica.dto.otp.UserSubmitOtp;
import com.spring.techpractica.maper.OtpMapper;
import com.spring.techpractica.repository.OtpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;

    private final CreateOtp createOtp;

    private final ValidationOtp validationOtp;

    @Transactional
    public OtpResponse createOtp(UserEmailSendOtp userEmailSendOtp) {
        return OtpMapper.mapOtp(createOtp.createOtp(userEmailSendOtp));
    }

    @Transactional
    public void validationOtp(UserSubmitOtp userSubmitOtp) {
        validationOtp.validationOtp(userSubmitOtp);
    }


}