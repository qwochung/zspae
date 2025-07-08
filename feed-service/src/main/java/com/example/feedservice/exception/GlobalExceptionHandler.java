package com.example.feedservice.exception;

import com.example.feedservice.constant.ErrorCode;
import com.example.feedservice.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getCode())
                .body(new ApiResponse<>(errorCode.getCode(),
                        errorCode.getMessage(), null));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),
                        "An unexpected error occurred", null));

    }
}
