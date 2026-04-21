package com.taller.bookstore.controller;

import com.taller.bookstore.dto.request.LoginRequest;
import com.taller.bookstore.dto.request.RegisterRequest;
import com.taller.bookstore.dto.response.AuthResponse;
import com.taller.bookstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/signin")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}