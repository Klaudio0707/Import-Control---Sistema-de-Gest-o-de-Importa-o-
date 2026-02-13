package com.claudio.importcontrol.config;

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
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity htpp) throws Exception {
      http
      .csrf(csrf -> csrf.disable())
      .authorizeHtppRequests(auth -< auth
        .anyRequest().permitAll() 
      );
      return http.build();  
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return BCryptPasswordEncorder
    }

}