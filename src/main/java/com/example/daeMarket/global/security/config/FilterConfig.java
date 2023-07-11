package com.example.daeMarket.global.security.config;

import com.example.daeMarket.global.security.JWT.JwtAuthenticationFilter;
import com.example.daeMarket.global.security.JWT.JwtTokenProvider;
import com.example.daeMarket.global.error.filter.GlobalExceptionFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

        private final JwtTokenProvider jwtTokenProvider;
        private final ObjectMapper objectMapper;

        @Override
        public void configure(HttpSecurity builder) {
        JwtAuthenticationFilter jwtTokenFilter = new JwtAuthenticationFilter(jwtTokenProvider);
        GlobalExceptionFilter exceptionFilter = new GlobalExceptionFilter(objectMapper);
        builder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(exceptionFilter, JwtAuthenticationFilter.class);
    }
    }
