package com.example.authservice.service;

import com.example.authservice.dto.request.CreateUserRequest;
import com.example.authservice.dto.request.LoginRequest;
import com.example.authservice.dto.request.RequestToken;
import com.example.authservice.dto.response.AuthenticateResponse;
import com.example.authservice.dto.response.UserResponse;

public interface AuthenticateService {
    AuthenticateResponse login (LoginRequest loginRequest);
    AuthenticateResponse refreshToken (RequestToken refreshToken);
    Boolean verifyToken (RequestToken token);

}
