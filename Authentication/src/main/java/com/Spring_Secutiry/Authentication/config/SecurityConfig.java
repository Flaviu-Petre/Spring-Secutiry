package com.Spring_Secutiry.Authentication.config;

import com.Spring_Secutiry.Authentication.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/employees/register").permitAll()
                        .requestMatchers("/api/test/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                        .requestMatchers("/api/employees/addEmployee").hasRole("ADMIN")
                        .requestMatchers("/api/employees/updateEmployee").hasRole("ADMIN")
                        .requestMatchers("/api/employees/deleteEmployee/**").hasRole("ADMIN")

                        .requestMatchers("/api/employees/allEmployees").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/employees/getEmployeeById/**").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .httpBasic(basic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}