package com.example.daeMarket.domain.user.exception;

import com.example.daeMarket.global.error.GlobalErrorCode;
import com.example.daeMarket.global.error.exception.SousoException;

public class InvalidCodeException extends SousoException {

    public static final SousoException EXCEPTION =
            new InvalidCodeException();

    private InvalidCodeException() {
        super(GlobalErrorCode.INVALID_CODE);
    }
}