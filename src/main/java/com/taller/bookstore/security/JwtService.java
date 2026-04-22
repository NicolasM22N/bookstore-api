package com.taller.bookstore.security;

import com.taller.bookstore.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String SECRET =
            "myultrasecurejwtsecretkeyforbookstoreproject2026token";

    private final long EXPIRATION = 86400000;

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + EXPIRATION)
                )
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}