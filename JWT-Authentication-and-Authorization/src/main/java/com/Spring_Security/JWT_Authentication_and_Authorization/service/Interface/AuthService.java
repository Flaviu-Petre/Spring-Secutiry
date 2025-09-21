package com.Spring_Security.JWT_Authentication_and_Authorization.service.Interface;

import org.springframework.security.core.Authentication;

public interface AuthService {
    Authentication authenticateUser(String username, String password);
    String generateToken(Authentication authentication);
    String login(String username, String password);
}
