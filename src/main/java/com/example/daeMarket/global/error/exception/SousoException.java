package com.example.daeMarket.global.error.exception;

import com.example.daeMarket.global.error.GlobalErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SousoException extends RuntimeException {

    private final GlobalErrorCode errorCode;

}