package com.ram.badgeram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // désactive CSRF (pour les appels API REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // autorisé sans token
                        .anyRequest().authenticated()              // tout le reste = sécurisé
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .httpBasic(httpBasic -> httpBasic.disable())   // désactive auth basique
                .formLogin(form -> form.disable());            // désactive login HTML

        return http.build();
    }
}
