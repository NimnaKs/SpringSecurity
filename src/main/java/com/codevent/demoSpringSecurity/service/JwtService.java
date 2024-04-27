package com.codevent.demoSpringSecurity.service;

import com.codevent.demoSpringSecurity.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails saveUser);
}
