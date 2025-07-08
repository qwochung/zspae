package com.example.feedservice.exception;

import com.example.feedservice.constant.ErrorCode;
import lombok.Data;

@Data
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
