package com.example.feedservice.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BAD_REQUEST (400,HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase()),
    NOT_FOUND (404, HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase()),

    // Feed
    FEED_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Feed not found"),
    FEED_ALREADY_EXISTS(400, HttpStatus.BAD_REQUEST, "Feed already exists"),
    FEED_CREATION_FAILED(500, HttpStatus.INTERNAL_SERVER_ERROR, "Feed creation failed"),


    // Comment
    COMMENT_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Comment not found"),
    COMMENT_CREATION_FAILED(500, HttpStatus.INTERNAL_SERVER_ERROR, "Comment creation failed"),
    COMMENT_ALREADY_EXISTS(400, HttpStatus.BAD_REQUEST, "Comment already exists");


    int code;
    HttpStatus httpStatus;
    String message;

    ErrorCode(int code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
