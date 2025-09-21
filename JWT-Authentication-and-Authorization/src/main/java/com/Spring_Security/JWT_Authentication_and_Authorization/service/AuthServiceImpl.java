package com.Spring_Security.JWT_Authentication_and_Authorization.service;

import com.Spring_Security.JWT_Authentication_and_Authorization.config.JwtTokenProvider;
import com.Spring_Security.JWT_Authentication_and_Authorization.service.Interface.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Authentication authenticateUser(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(authToken);

            return authentication;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }

    @Override
    public String generateToken(Authentication authentication) {
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String login(String username, String password) {
        try {
            Authentication authentication = authenticateUser(username, password);

            String token = generateToken(authentication);

            return token;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Login failed: " + e.getMessage(), e);
        }
    }

    public boolean validateCredentials(String username, String password) {
        try {
            authenticateUser(username, password);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    public Authentication getAuthenticatedUser(String username, String password) {
        return authenticateUser(username, password);
    }
}