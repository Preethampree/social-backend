package com.example.social.media.Controller;


import com.example.social.media.Service.EmailService;
import com.example.social.media.Service.OtpStore;
import com.example.social.media.Util.OtpUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


    private final EmailService emailService;
    private final OtpStore otpStore;

    // 1️⃣ SEND OTP
    @PostMapping("/request-otp")
    public String requestOtp(@RequestParam String email) {

        String otp = OtpUtil.generateOtp();
        otpStore.saveOtp(email, otp);
        emailService.sendOtp(email, otp);

        return "OTP sent to email";
    }

    // 2️⃣ VERIFY OTP & CREATE SESSION
    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp,
            HttpSession session
    ) {
        boolean valid = otpStore.verifyOtp(email, otp);

        if (!valid) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        session.setAttribute("LOGGED_IN_EMAIL", email);
        otpStore.clearOtp(email);

        return "Login successful";
    }

    // 3️⃣ LOGOUT
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }

}
