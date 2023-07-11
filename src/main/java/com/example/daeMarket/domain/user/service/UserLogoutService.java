package com.example.daeMarket.domain.user.service;

import com.example.daeMarket.domain.auth.domain.RefreshToken;
import com.example.daeMarket.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.daeMarket.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.daeMarket.domain.user.domain.User;
import com.example.daeMarket.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserLogoutService {

    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        RefreshToken refreshToken = refreshTokenRepository.findById(user.getEmail())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }
}
