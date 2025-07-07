package com.example.authservice.service.impl;

import com.example.authservice.constant.ErrorCode;
import com.example.authservice.entity.InvalidToken;
import com.example.authservice.exception.AppException;
import com.example.authservice.repository.InvalidTokenRepository;
import com.example.authservice.service.InvalidTokenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvalidTokenServiceIml implements InvalidTokenService {
    InvalidTokenRepository invalidTokenRepository;


    @Override
    public InvalidToken createInvalidToken(InvalidToken invalidToken) {
        if (invalidToken.getId().isEmpty()|| invalidToken.getExpiresTime() == null) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
        return invalidTokenRepository.save(invalidToken);
    }

    @Override
    public Boolean existsInvalidToken(String id) {
        return invalidTokenRepository.existsById(id);
    }
}
