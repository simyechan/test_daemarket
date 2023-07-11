package com.example.daeMarket.domain.auth.exception;

import com.example.daeMarket.global.error.GlobalErrorCode;
import com.example.daeMarket.global.error.exception.SousoException;

public class RefreshTokenNotFoundException extends SousoException {

    public static final SousoException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(GlobalErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
