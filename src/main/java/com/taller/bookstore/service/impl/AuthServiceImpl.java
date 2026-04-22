package com.taller.bookstore.service.impl;

import com.taller.bookstore.dto.request.LoginRequest;
import com.taller.bookstore.dto.request.RegisterRequest;
import com.taller.bookstore.dto.response.AuthResponse;
import com.taller.bookstore.entity.Role;
import com.taller.bookstore.entity.User;
import com.taller.bookstore.exception.custom.DuplicateResourceException;
import com.taller.bookstore.exception.custom.UnauthorizedAccessException;
import com.taller.bookstore.repository.UserRepository;
import com.taller.bookstore.security.JwtService;
import com.taller.bookstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UnauthorizedAccessException("Invalid credentials"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new UnauthorizedAccessException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                token,
                86400000L,
                user.getRole().name()
        );
    }
}