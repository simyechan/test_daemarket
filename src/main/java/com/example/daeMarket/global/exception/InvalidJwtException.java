package com.example.daeMarket.global.exception;

import com.example.daeMarket.global.error.GlobalErrorCode;
import com.example.daeMarket.global.error.exception.SousoException;

public class InvalidJwtException extends SousoException {

    public static final SousoException EXCEPTION =
            new InvalidJwtException();

    private InvalidJwtException() {
        super(GlobalErrorCode.INVALID_JWT);
    }
}
