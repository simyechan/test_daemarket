package com.example.daeMarket.domain.user.service;

import com.example.daeMarket.domain.auth.exception.PasswordMisMatchException;
import com.example.daeMarket.domain.user.controller.dto.request.UpdatePasswordRequest;
import com.example.daeMarket.domain.user.domain.User;
import com.example.daeMarket.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdatePasswordService {
        private final UserFacade userFacade;
        private final PasswordEncoder passwordEncoder;

        @Transactional
        public void execute(UpdatePasswordRequest request) {
            User user = userFacade.getCurrentUser();

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw PasswordMisMatchException.EXCEPTION;
            }

            user.setPassword(passwordEncoder.encode(request.getNewpassword()));
        }
}
