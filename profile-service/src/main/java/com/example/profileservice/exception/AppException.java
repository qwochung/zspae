package com.example.profileservice.exception;

import com.example.profileservice.contants.ErrorCode;
import lombok.Data;

@Data
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
