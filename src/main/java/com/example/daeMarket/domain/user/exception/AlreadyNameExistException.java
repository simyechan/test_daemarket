package com.example.daeMarket.domain.user.exception;

import com.example.daeMarket.global.error.GlobalErrorCode;
import com.example.daeMarket.global.error.exception.SousoException;

public class AlreadyNameExistException extends SousoException {

    public static final SousoException EXCEPTION =
            new AlreadyNameExistException();

    private AlreadyNameExistException() {
        super(GlobalErrorCode.ALREADY_NAME_EXIST);
    }
}
