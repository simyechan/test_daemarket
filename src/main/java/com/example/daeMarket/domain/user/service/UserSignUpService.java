package com.example.daeMarket.domain.user.service;

import com.example.daeMarket.domain.auth.controller.dto.response.UserTokenResponse;
import com.example.daeMarket.domain.user.facade.UserFacade;
import com.example.daeMarket.global.security.JWT.JwtProperties;
import com.example.daeMarket.global.security.JWT.JwtTokenProvider;
import com.example.daeMarket.domain.user.controller.dto.request.UserSignUpRequest;
import com.example.daeMarket.domain.user.domain.User;
import com.example.daeMarket.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public UserTokenResponse execute(UserSignUpRequest request) {
        userFacade.checkUserExists(request.getEmail());

        User user = userRepository.save(User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .st_num(request.getSt_num())
                .password(passwordEncoder.encode(request.getPassword()))
                .like_num(request.getLike_num())
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}
