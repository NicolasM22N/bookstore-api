package com.taller.bookstore.mapper;

import com.taller.bookstore.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(User user) {
        return user;
    }

    public User toResponse(User user) {
        return user;
    }
}