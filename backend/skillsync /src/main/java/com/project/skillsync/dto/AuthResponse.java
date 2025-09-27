package com.project.skillsync.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AuthResponse {
    private String username;
    private String email;
    private Set<String> roles;
    private String message;

    private String token;

}
