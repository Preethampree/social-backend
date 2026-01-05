package com.example.social.media.Service;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpStore {

    private static class OtpData {
        String otp;
        LocalDateTime expiry;
    }

    private final Map<String, OtpData> otpMap = new HashMap<>();

    public void saveOtp(String email, String otp) {
        OtpData data = new OtpData();
        data.otp = otp;
        data.expiry = LocalDateTime.now().plusMinutes(5);
        otpMap.put(email, data);
    }

    public boolean verifyOtp(String email, String otp) {
        OtpData data = otpMap.get(email);
        if (data == null) return false;
        if (data.expiry.isBefore(LocalDateTime.now())) return false;
        return data.otp.equals(otp);
    }

    public void clearOtp(String email) {
        otpMap.remove(email);
    }

}
