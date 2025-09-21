package com.Spring_Security.JWT_Authentication_and_Authorization.controller;

import com.Spring_Security.JWT_Authentication_and_Authorization.dao.JwtAuthResponse;
import com.Spring_Security.JWT_Authentication_and_Authorization.dao.LoginDto;
import com.Spring_Security.JWT_Authentication_and_Authorization.service.Interface.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authService.authenticateUser(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            );
            String token = authService.generateToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String role = userDetails.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

            JwtAuthResponse response = new JwtAuthResponse(
                    token,
                    userDetails.getUsername(),
                    role,
                    jwtExpirationMs
            );

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Login failed: " + e.getMessage());
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody LoginDto loginDto) {
        try {
            String token = authService.login(loginDto.getUsername(), loginDto.getPassword());

            JwtAuthResponse response = new JwtAuthResponse(token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Authentication failed");
        }
    }


    @GetMapping("/validate")
    public ResponseEntity<String> validateToken() {
        return ResponseEntity.ok("Token is valid");
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("This is a public authentication endpoint");
    }
}