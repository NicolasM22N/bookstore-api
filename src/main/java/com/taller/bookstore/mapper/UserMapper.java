package com.taller.bookstore.mapper;

import com.taller.bookstore.dto.request.RegisterRequest;
import com.taller.bookstore.dto.response.UserResponse;
import com.taller.bookstore.entity.Role;
import com.taller.bookstore.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request, PasswordEncoder passwordEncoder) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() == null ? Role.ROLE_USER : request.getRole())
                .build();
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
