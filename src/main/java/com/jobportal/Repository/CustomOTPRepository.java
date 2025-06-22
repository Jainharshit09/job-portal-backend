package com.jobportal.Repository;

import com.jobportal.Entity.OTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.time.Duration;

@Repository
public class CustomOTPRepository {

    @Autowired
    private RedisTemplate<String, OTP> redisTemplate;

    public void saveOtp(String email, OTP otp) {
        redisTemplate.opsForValue().set(email, otp, Duration.ofMinutes(5)); // Set OTP with TTL
    }

    public OTP getOtp(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    public void removeOtp(String email) {
        redisTemplate.delete(email);
    }

    public boolean otpExists(String email) {
        return redisTemplate.hasKey(email);
    }
}