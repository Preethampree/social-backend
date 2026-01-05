package com.example.social.media.Service;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Login OTP For The GateWay");
        message.setText(
                "🚪 Welcome to Gatwway – Your Social Gateway\n\n" +
                        "Meet Gatwway – the social app that connects you to what (and who) matters most.\n\n" +
                        "Think of it as your personal gateway to:\n" +
                        "✅ Communities you'll love\n" +
                        "✅ Content that inspires\n" +
                        "✅ Conversations that matter\n\n" +
                        "Your verification OTP is: " + otp + "\n" +
                        "(Valid for 10 minutes)\n\n" +
                        "This is just the beginning. Come help us build it.\n\n" +
                        "Best,\n" +
                        "The Gatwway Team"
        );
        mailSender.send(message);
    }


}
