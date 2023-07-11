package com.example.daeMarket.domain.auth.service;

import com.example.daeMarket.domain.auth.controller.dto.response.UserTokenRefreshResponse;
import com.example.daeMarket.domain.auth.domain.RefreshToken;
import com.example.daeMarket.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.daeMarket.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.daeMarket.global.security.JWT.JwtProperties;
import com.example.daeMarket.global.security.JWT.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserTokenRefreshService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public UserTokenRefreshResponse execute(String refreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(jwtTokenProvider.parseToken(refreshToken))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(redisRefreshToken.getEmail());
        redisRefreshToken.updateToken(newRefreshToken, jwtProperties.getRefreshExp());

        String accessToken = jwtTokenProvider.generateAccessToken(redisRefreshToken.getEmail());
        return UserTokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();
    }

}
