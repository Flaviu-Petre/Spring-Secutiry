package com.Spring_Security.JWT_Authentication_and_Authorization.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String role;
    private long expiresIn;

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public JwtAuthResponse(String accessToken, String username, String role) {
        this.accessToken = accessToken;
        this.username = username;
        this.role = role;
    }

    public JwtAuthResponse(String accessToken, String username, String role, long expiresIn) {
        this.accessToken = accessToken;
        this.username = username;
        this.role = role;
        this.expiresIn = expiresIn;
    }
}