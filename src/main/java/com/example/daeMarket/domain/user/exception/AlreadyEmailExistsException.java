package com.example.daeMarket.domain.user.exception;

import com.example.daeMarket.global.error.GlobalErrorCode;
import com.example.daeMarket.global.error.exception.SousoException;

public class AlreadyEmailExistsException extends SousoException {

    public static final SousoException EXCEPTION =
            new AlreadyEmailExistsException();

    private AlreadyEmailExistsException() {
        super(GlobalErrorCode.ALREADY_EMAIL_EXISTS);
    }
}