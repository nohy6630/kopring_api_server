package com.example.kopring_api_server.domain.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .formLogin(c -> c.disable())
//                .csrf(c -> c.disable())
//                .authorizeHttpRequests(c -> c
//                        .requestMatchers(
//                                "/**").permitAll()
//                        .anyRequest().authenticated())
//                .headers(c -> c.frameOptions(c2 -> c2.disable()));
//        return http.build();
//    }
//}
