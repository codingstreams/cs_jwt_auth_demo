package com.codingstreams.jwtauth.config;

import com.codingstreams.jwtauth.security.JwtAuthEntryPoint;
import com.codingstreams.jwtauth.security.JwtAuthFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Create JwtAuthFilter bean
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter();
    }

    // Create PasswordEncoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // Create SecurityFilterChain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CORS
        http.cors().disable();

        // Change session management to STATELESS
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add exception handler
        AuthenticationEntryPoint jwtAuthEntryPoint = new JwtAuthEntryPoint();
        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint);

        // Authorize http requests
        http.authorizeHttpRequests(matcher -> {
            matcher.requestMatchers("/").permitAll()
                    .requestMatchers("/app-auth/token/**").permitAll()
                    .anyRequest().authenticated();
        });

        // Add JWT authentication filter
        http.addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
