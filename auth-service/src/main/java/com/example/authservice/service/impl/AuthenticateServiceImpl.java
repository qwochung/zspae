package com.example.authservice.service.impl;

import com.example.authservice.constant.ErrorCode;
import com.example.authservice.dto.request.LoginRequest;
import com.example.authservice.dto.response.UserResponse;
import com.example.authservice.entity.User;
import com.example.authservice.exception.AppException;
import com.example.authservice.mapper.UserMapper;
import com.example.authservice.service.AuthenticateService;
import com.example.authservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticateServiceImpl  implements AuthenticateService {

    UserService userService;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new AppException(ErrorCode.NOT_FOUND));
        boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatch) {
            throw new AppException(ErrorCode.INVALID_USERNAME_OR_PASSWORD);
        }
        return userMapper.toUserResponse(user);
    }











    private record TokenInfo(String token, Date expires) {}
}

