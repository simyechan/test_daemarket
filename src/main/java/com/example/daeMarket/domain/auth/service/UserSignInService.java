package com.example.daeMarket.domain.auth.service;

import com.example.daeMarket.domain.auth.controller.dto.response.UserTokenResponse;
import com.example.daeMarket.domain.auth.controller.dto.request.UserSignInRequest;
import com.example.daeMarket.domain.auth.exception.PasswordMisMatchException;
import com.example.daeMarket.domain.user.domain.User;
import com.example.daeMarket.domain.user.domain.repository.UserRepository;
import com.example.daeMarket.global.security.JWT.JwtTokenProvider;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Builder
public class UserSignInService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public UserTokenResponse login(UserSignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Email 입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw PasswordMisMatchException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .refreshToken(refreshToken)
                .build();
    }
}
