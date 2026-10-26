package com.playpadel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
            // API REST amb tokens: no cal protecció CSRF (és per a webs amb galetes)
            .csrf(csrf -> csrf.disable())

            // Sense sessions al servidor: cada petició s'identifica amb el seu token
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Qui pot accedir a cada ruta
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/error").permitAll()   // registre i login: públics
                .requestMatchers("/api/admin/**").hasRole("ADMIN")       // administració: només ADMIN
                .anyRequest().authenticated()                            // la resta: cal token vàlid
            )

            // Sense token vàlid: respondre 401 (no identificat) en lloc de 403
            .exceptionHandling(e -> e.authenticationEntryPoint(
                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

            // El nostre filtre llegeix el token abans del filtre estàndard de Spring
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Xifratge de contrasenyes amb bcrypt (hash irreversible amb sal)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}