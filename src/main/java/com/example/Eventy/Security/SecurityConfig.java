package com.example.Eventy.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HttpSecurity de manière fluide
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()  // Autoriser toutes les requêtes sans authentification
                )
                .csrf(AbstractHttpConfigurer::disable);  // Désactiver CSRF de manière explicite

        return http.build();
    }
}



