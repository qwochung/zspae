package com.example.authservice.exception;

import com.example.authservice.constant.ErrorCode;
import lombok.Data;

@Data
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getResult());
        this.errorCode = errorCode;
    }
}
