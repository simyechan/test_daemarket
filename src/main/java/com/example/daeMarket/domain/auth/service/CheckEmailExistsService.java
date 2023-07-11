package com.example.daeMarket.domain.auth.service;

import com.example.daeMarket.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckEmailExistsService {
    private final UserFacade userFacade;

    public void execute(String email) {
        userFacade.checkEmailExists(email);
    }
}