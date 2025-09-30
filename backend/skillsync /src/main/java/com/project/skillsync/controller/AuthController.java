package com.project.skillsync.controller;

import com.project.skillsync.dto.LoginRequest;
import com.project.skillsync.dto.OtpVerificationRequest;
import com.project.skillsync.dto.RegisterRequest;
import com.project.skillsync.dto.AuthResponse;
import com.project.skillsync.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody OtpVerificationRequest request) {
        return ResponseEntity.ok(authService.verifyOtpAndLogin(request));
    }


}
