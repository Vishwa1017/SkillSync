package com.project.skillsync.service;

import com.project.skillsync.dto.AuthResponse;
import com.project.skillsync.dto.LoginRequest;
import com.project.skillsync.dto.RegisterRequest;
import com.project.skillsync.exception.DuplicateResourceException;
import com.project.skillsync.model.Role;
import com.project.skillsync.model.User;
import com.project.skillsync.repository.RoleRepository;
import com.project.skillsync.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthResponse registerUser(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }
        if(userRepository.existsByUsername(request.getUsername())){
            throw new DuplicateResourceException("Username already in use");
        }
       // Get the default role
        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        // Create and save new user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRoles(new HashSet<>(List.of(roleUser)));
        userRepository.save(user);

        //  Build response DTO
        AuthResponse response = new AuthResponse();
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        response.setMessage("User registered successfully");
        return response;
    }

    public AuthResponse loginUser(LoginRequest request) {
        // 1️⃣ Authenticate credentials (this calls CustomUserDetailsService + PasswordEncoder behind the scenes)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        // 2️⃣ Fetch user from DB (to include roles, email in response)
        User user = userRepository.findByEmail(request.getUsernameOrEmail())
                .or(() -> userRepository.findByUsername(request.getUsernameOrEmail()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3️⃣ Generate JWT token
        String token = jwtService.generateToken(user.getUsername());

        // 4️⃣ Build response DTO
        AuthResponse response = new AuthResponse();
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet()));
        response.setMessage("Login successful");
        response.setToken(token);

        return response;
    }


}
