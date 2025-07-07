package com.example.authservice.service;

import com.example.authservice.entity.InvalidToken;

public interface InvalidTokenService {
    InvalidToken createInvalidToken(InvalidToken invalidToken);
    Boolean existsInvalidToken(String id);
}
