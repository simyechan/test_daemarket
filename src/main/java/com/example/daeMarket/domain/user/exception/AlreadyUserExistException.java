package com.example.daeMarket.domain.user.exception;

import com.example.daeMarket.global.error.GlobalErrorCode;
import com.example.daeMarket.global.error.exception.SousoException;

public class AlreadyUserExistException extends SousoException {

    public static final SousoException EXCEPTION =
            new AlreadyUserExistException();

    private AlreadyUserExistException() {
        super(GlobalErrorCode.ALREADY_NICKNAME_EXIST);
    }
}
