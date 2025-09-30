package com.project.skillsync.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;


@RequiredArgsConstructor
@Service
public class OtpService {

    private final StringRedisTemplate redisTemplate;
    private final EmailService emailService; // 👈 Injected email sender

    private static final long OTP_EXPIRY_MINUTES = 5;

    public String createOtp(String email) {
        // 1. Generate 6-digit OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        // 2. Store OTP in Redis with TTL
        String key = "otp:" + email;
        redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(OTP_EXPIRY_MINUTES));

        // 3. Send OTP via Email
        emailService.sendOtpEmail(email, otp);

        return otp;
    }
    public boolean verifyOtp(String email, String otpProvided) {
        String key = "otp:" + email;
        String storedOtp = redisTemplate.opsForValue().get(key);

        if (storedOtp != null && storedOtp.equals(otpProvided)) {
            // OTP is correct — delete it to prevent reuse
            redisTemplate.delete(key);
            return true;
        }

        return false; // OTP expired or invalid
    }
}
